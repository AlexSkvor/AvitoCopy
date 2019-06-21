package com.avito_copy.demo.controllers

import com.avito_copy.demo.controllers.CarsHelper.getCars
import com.avito_copy.demo.controllers.CarsHelper.getPossibleBodyTypes
import com.avito_copy.demo.controllers.CarsHelper.getPossibleColors
import com.avito_copy.demo.controllers.CarsHelper.getPossibleMarks
import com.avito_copy.demo.controllers.CarsHelper.getPossibleSorts
import com.avito_copy.demo.entities.front.FrontCar
import com.avito_copy.demo.extensions.*
import com.avito_copy.demo.responses.BaseResponse
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
                   @RequestParam(value = "sort", required = false, defaultValue = "Дешевые сверху") sort: String,
                   @RequestParam(value = "minPrice", required = false, defaultValue = "0") minPrice: Int,
                   @RequestParam(value = "maxPrice", required = false, defaultValue = "999999") maxPrice: Int,
                   @RequestParam(value = "minYear", required = false, defaultValue = "0") minYear: Int,
                   @RequestParam(value = "maxYear", required = false, defaultValue = "999999") maxYear: Int
    ): BaseResponse<FrontCar> {

        val cars = getCars()
        if (cars.isEmpty()) return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, arrayOf())

        validateTradeMarks(tradeMarks)?.let { return it }
        validateColors(colors)?.let { return it }
        validateBodyTypes(bodyTypes)?.let { return it }
        validateSort(sort)?.let { return it }

        val temp: List<FrontCar> = cars.asSequence()
                .filter { colorsFilter(it, colors) }
                .filter { bodyTypesFilter(it, bodyTypes) }
                .filter { it.price.biggerEqualsThen(minPrice) }
                .filter { it.price.lessEqualsThen(maxPrice) }
                .filter { it.year.biggerEqualsThen(minYear) }
                .filter { it.year.lessEqualsThen(maxYear) }
                .filter { tradeMarksFilter(it, tradeMarks) }
                .distinct()
                .toList()
                .sortedByType(sort)

        val medianCost = middleCost(temp)

        val data = temp.withSkipTake(skip, take).toTypedArray()
        data.setIds()

        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, data, medianCost = medianCost)
    }

    private fun validateTradeMarks(marks: Array<String>): BaseResponse<FrontCar>? {
        if (marks.isEmpty()) return null
        val permitted = getPossibleMarks()
        val bad = marks.filter { !permitted.contains(it) }
        return if (bad.isEmpty()) null
        else BaseResponse(STATUS_ERROR, BAD_REQUEST, arrayOf(), message = wrongArguments(bad, "info/cars/tradeMarks"))
    }

    private fun validateColors(colors: Array<String>): BaseResponse<FrontCar>? {
        if (colors.isEmpty()) return null
        val permitted = getPossibleColors()
        val bad = colors.filter { !permitted.contains(it) }
        return if (bad.isEmpty()) null
        else BaseResponse(STATUS_ERROR, BAD_REQUEST, arrayOf(), message = wrongArguments(bad, "info/cars/colors"))
    }

    private fun validateBodyTypes(bodies: Array<String>): BaseResponse<FrontCar>? {
        if (bodies.isEmpty()) return null
        val permitted = getPossibleBodyTypes()
        val bad = bodies.filter { !permitted.contains(it) }
        return if (bad.isEmpty()) null
        else BaseResponse(STATUS_ERROR, BAD_REQUEST, arrayOf(), message = wrongArguments(bad, "info/cars/bodyTypes"))
    }

    private fun validateSort(sort: String): BaseResponse<FrontCar>? {
        if (sort.isEmpty()) return null
        val permitted = getPossibleSorts()
        val bad = if (permitted.contains(sort)) null else sort//bodies.filter { !permitted.contains(it) }
        return if (bad.isNullOrEmpty()) null
        else BaseResponse(STATUS_ERROR, BAD_REQUEST, arrayOf(), message = wrongArguments(listOf(bad), "info/cars/sorts"))
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
                "Новые" -> this.toMutableList().sortedByDescending { it.year.toIntOrzero() }
                "Дешевые" -> this.toMutableList().sortedBy { it.price.toIntOrBig() }
                "Дорогие" -> this.toMutableList().sortedByDescending { it.price.toIntOrzero() }
                "Большой пробег" -> this.toMutableList().sortedByDescending { it.mileage.filterDigits().toIntOrzero() }
                "Маленький пробег" -> this.toMutableList().sortedBy { it.mileage.filterDigits().toIntOrBig() }
                else -> this.toMutableList().sortedBy { it.price.toIntOrBig() }
            }
}