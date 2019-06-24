package com.example.devapi.controllers

import com.example.devapi.controllers.requests.MarkWithModels
import com.example.devapi.controllers.requests.TradeMarksRequest
import com.example.devapi.database.dao.CarsDao
import com.example.devapi.controllers.responses.BaseResponse
import com.example.devapi.utils.*
import com.google.gson.Gson
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created on 6/20/2019
 * @author AlexSkvor
 * */

@RestController
@RequestMapping("/search")
class SearchController(
        private val carsRepository: CarsDao
) {

    @GetMapping("/cars")
    fun searchCars(@RequestParam(value = "skip", required = false, defaultValue = "0") skip: Int,
                   @RequestParam(value = "take", required = false, defaultValue = "20") take: Int,
                   @RequestParam(value = "colors", required = false, defaultValue = "") colors: Array<String>,
                   @RequestParam(value = "bodyTypes", required = false, defaultValue = "") bodyTypes: Array<String>,
                   @RequestParam(value = "sort", required = false, defaultValue = "Дешевые") sort: String,
                   @RequestParam(value = "minPrice", required = false, defaultValue = "0") minPrice: Int,
                   @RequestParam(value = "maxPrice", required = false, defaultValue = "99999999") maxPrice: Int,
                   @RequestParam(value = "minYear", required = false, defaultValue = "0") minYear: Int,
                   @RequestParam(value = "maxYear", required = false, defaultValue = "999999") maxYear: Int,
                   @RequestParam(value = "dangerMileage", required = false, defaultValue = "50") dangerMileage: Int,
                   @RequestParam(value = "dangerPrice", required = false, defaultValue = "50") dangerPrice: Int,
                   @RequestParam(value = "cities", required = false, defaultValue = "") cities: Array<String>,
                   @RequestParam(value = "sources", required = false, defaultValue = "") sources: Array<String>,
                   @RequestParam(value = "filterResellers", required = false, defaultValue = "false") filterResellers: Boolean,
                   @RequestParam(value = "tradeMarksRequest", required = false, defaultValue = "[]") tradeMarksRequest: String
    ): BaseResponse<FrontCar> {

        val tradeMarks = try {
            Gson().fromJson(tradeMarksRequest, TradeMarksRequest::class.java)
        } catch (e: Exception) {
            null
        }

        val cars = carsRepository.getAllByYearIsBetweenAndPriceIsBetweenOrderByPrice(minYear, maxYear, minPrice, maxPrice)
                .map { FrontCar(it) }
                .asSequence()
                .filter { colorsFilter(it, colors) }
                .filter { bodyTypesFilter(it, bodyTypes) }
                .filter { citiesFilter(it, cities) }
                .filter { sourcesFilter(it, sources) }
                .filter { tradeMarksFilter(it, tradeMarks?.marksAndModels) }
                .toList()
                .filterResellers(filterResellers)
                .sortedByType(sort)

        val (middlePrice, middleMileage) = middleCostAndMileage(cars)
        val data = cars.withSkipTake(skip, take).toTypedArray()
        data.setIds()
        data.setDangerouslyMileageFlags(dangerMileage, middleMileage)
        data.setDangerouslyPriceFlags(dangerPrice, middlePrice)

        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, data, medianCost = middlePrice, medianMileage = middleMileage)
    }

    private fun colorsFilter(car: FrontCar, colors: Array<String>): Boolean {
        return colors.isEmpty() || colors.contains(car.color)
    }

    private fun citiesFilter(car: FrontCar, cities: Array<String>): Boolean {
        return cities.isEmpty() || cities.contains(car.city)
    }

    private fun sourcesFilter(car: FrontCar, sources: Array<String>): Boolean {
        return sources.isEmpty() || sources.contains(car.source)
    }

    private fun bodyTypesFilter(car: FrontCar, bodyTypes: Array<String>): Boolean {
        return bodyTypes.isEmpty() || bodyTypes.contains(car.bodyType)
    }

    private fun tradeMarksFilter(car: FrontCar, marksAndModels: List<MarkWithModels>?): Boolean {
        if (marksAndModels.isNullOrEmpty()) return true

        marksAndModels.forEach {
            if (it.mark.contains(car.tradeMark) && car.tradeMark.isNotEmpty()) {
                if (it.models.isEmpty()) return true
                it.models.forEach { model ->
                    if (model.contains(car.model) && car.model.isNotEmpty())
                        return true
                }
            }
        }
        return false
    }

    private fun List<FrontCar>.filterResellers(filter: Boolean): List<FrontCar> =
            if (filter) ResellersFilter.filter(this) else this

    private fun middleCostAndMileage(cars: List<FrontCar>): Pair<Int, Int> {
        if (cars.isEmpty()) return Pair(1, 1)
        var cost = 1
        var mileage = 1
        cars.forEach {
            cost += it.price
            mileage += it.mileage
        }

        return Pair(cost / cars.size, mileage / cars.size)
    }

    private fun List<FrontCar>.sortedByType(sort: String): List<FrontCar> =
            when (sort) {
                "Старые" -> this.toMutableList().sortedBy { it.year }
                "Новые" -> this.toMutableList().sortedByDescending { it.year }
                "Дешевые" -> this.toMutableList().sortedBy { it.price }
                "Дорогие" -> this.toMutableList().sortedByDescending { it.price }
                "Большой_пробег" -> this.toMutableList().sortedByDescending { it.mileage }
                "Маленький_пробег" -> this.toMutableList().sortedBy { it.mileage }
                else -> this.toMutableList().sortedBy { it.price }
            }
}