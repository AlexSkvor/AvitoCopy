package com.example.devapi.controllers

import com.example.devapi.entities.front.FrontCar
import com.example.devapi.responses.BaseResponse
import com.example.devapi.utils.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

/**
 * Created on 6/20/2019
 * @author AlexSkvor
 * */

@RestController
@RequestMapping("/search")
class SearchController {

    @GetMapping("/cars")
    fun searchCars(@RequestParam(value = "skip", required = false, defaultValue = "0") skip: Int,
                   @RequestParam(value = "take", required = false, defaultValue = "3") take: Int,
                   @RequestParam(value = "tradeMarks", required = false, defaultValue = "") tradeMarks: Array<String>,
                   @RequestParam(value = "colors", required = false, defaultValue = "") colors: Array<String>,
                   @RequestParam(value = "bodyTypes", required = false, defaultValue = "") bodyTypes: Array<String>,
                   @RequestParam(value = "models", required = false, defaultValue = "") models: Array<String>,
                   @RequestParam(value = "sort", required = false, defaultValue = "Дешевые") sort: String,
                   @RequestParam(value = "minPrice", required = false, defaultValue = "0") minPrice: Int,
                   @RequestParam(value = "maxPrice", required = false, defaultValue = "999999") maxPrice: Int,
                   @RequestParam(value = "minYear", required = false, defaultValue = "0") minYear: Int,
                   @RequestParam(value = "maxYear", required = false, defaultValue = "999999") maxYear: Int
    ): BaseResponse<FrontCar> {

        /*val cars = getCars()
        if (cars.isEmpty()) return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, arrayOf())

        validateTradeMarks(tradeMarks)?.let { return errorResponse(it) }
        validateColors(colors)?.let { return errorResponse(it) }
        validateBodyTypes(bodyTypes)?.let { return errorResponse(it) }
        validateSort(sort)?.let { return errorResponse(it) }
        validateModels(tradeMarks, models)?.let { return errorResponse(it) }

        val temp: List<FrontCar> = cars.asSequence()
                .filter { colorsFilter(it, colors) }
                .filter { bodyTypesFilter(it, bodyTypes) }
                .filter { it.price.biggerEqualsThen(minPrice) }
                .filter { it.price.lessEqualsThen(maxPrice) }
                .filter { it.year.biggerEqualsThen(minYear) }
                .filter { it.year.lessEqualsThen(maxYear) }
                .filter { tradeMarksFilter(it, tradeMarks) }
                .filter { modelsFilter(it, models) }
                .distinct()
                .toList()
                .sortedByType(sort)

        val medianCost = middleCost(temp)

        val data = temp.withSkipTake(skip, take).toTypedArray()
        data.setIds()*/

        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, arrayOf())
    }

    private fun tradeMarksFilter(car: FrontCar, marks: Array<String>): Boolean {
        return marks.isEmpty() || marks.contains(car.tradeMark)
    }

    private fun colorsFilter(car: FrontCar, colors: Array<String>): Boolean {
        return colors.isEmpty() || colors.contains(car.color)
    }

    private fun bodyTypesFilter(car: FrontCar, bodyTypes: Array<String>): Boolean {
        return bodyTypes.isEmpty() || bodyTypes.contains(car.bodyType)
    }

    private fun modelsFilter(car: FrontCar, models: Array<String>): Boolean {
        return models.isEmpty() || models.contains(car.model)
    }

    private fun Array<FrontCar>.setIds() {
        var iter = 0
        this.forEach {
            it.id = "$iter"
            iter++
        }
    }

    private fun middleCost(cars: List<FrontCar>): Int {
        if (cars.isEmpty()) return 0
        var sum = 0
        return try {
            cars.forEach { sum += it.price.toInt() }
            sum / cars.size
        } catch (e: Exception) {
            -1
        }
    }

    private fun List<FrontCar>.sortedByType(sort: String): List<FrontCar> =
            when (sort) {
                "Старые" -> this.toMutableList().sortedBy { it.year.toIntOrBig() }
                "Новые" -> this.toMutableList().sortedByDescending { it.year.toIntOrZero() }
                "Дешевые" -> this.toMutableList().sortedBy { it.price.toIntOrBig() }
                "Дорогие" -> this.toMutableList().sortedByDescending { it.price.toIntOrZero() }
                "Большой_пробег" -> this.toMutableList().sortedByDescending { it.mileage.filterDigits().toIntOrZero() }
                "Маленький_пробег" -> this.toMutableList().sortedBy { it.mileage.filterDigits().toIntOrBig() }
                else -> this.toMutableList().sortedBy { it.price.toIntOrBig() }
            }
}