package com.avito_copy.demo.controllers

import com.avito_copy.demo.entities.front.FrontCar
import com.avito_copy.demo.extensions.withSkipTake
import com.avito_copy.demo.extensions.wrongArguments
import com.avito_copy.demo.responses.BaseResponse
import com.google.gson.Gson
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.lang.Exception

/**
 * Created on 6/20/2019
 * @author AlexSkvor
 * */

@RestController
@RequestMapping("/search")
class SearchController {

    private val gson = Gson()

    @GetMapping("/cars")
    fun searchCars(@RequestParam(value = "skip", required = false, defaultValue = "0") skip: Int,
                   @RequestParam(value = "take", required = false, defaultValue = "3") take: Int,
                   @RequestParam(value = "tradeMarks", required = false, defaultValue = "") tradeMarks: Array<String>
    ): BaseResponse<FrontCar> {

        val cars = getCars()
        if (cars.isEmpty()) return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, arrayOf())

        validateTradeMarks(tradeMarks)?.let { return it }

        val temp = cars.distinct()
                .filter { tradeMarksFilter(it, tradeMarks) }
                .distinct()

        val medianCost = middleCost(temp)
        val data = temp
                .withSkipTake(skip, take)
                .toTypedArray()

        data.setIds()
        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, data, medianCost = medianCost)
    }

    private fun validateTradeMarks(marks: Array<String>): BaseResponse<FrontCar>? {
        val permitted = getPossibleMarks()
        val bad = marks.filter { !permitted.contains(it) }
        return if (bad.isEmpty()) null
        else BaseResponse(STATUS_ERROR, BAD_REQUEST, arrayOf(), message = wrongArguments(bad, "search/cars/tradeMarks"))
    }

    private fun tradeMarksFilter(car: FrontCar, marks: Array<String>): Boolean {
        return marks.contains(car.tradeMark.trim())
    }

    @GetMapping("/cars/tradeMarks")
    fun getTradeMarksList(): BaseResponse<String> {
        val data = getPossibleMarks().toTypedArray()
        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, data)
    }

    private fun getPossibleMarks(): List<String> {
        return try {
            File("allTradeMarks.txt").readLines()
        } catch (e: Exception) {
            println("Tried to reed trade marks from file $e")
            listOf()
        }
    }

    private fun getCars(): List<FrontCar> {
        val carsFile = File("allCars.txt")
        if (!carsFile.exists()) return listOf()
        val cars = mutableListOf<FrontCar>()
        carsFile.forEachLine { cars.add(gson.fromJson(it, FrontCar::class.java)) }
        return cars
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
        cars.forEach { sum += it.price.toInt() }
        return sum / cars.size
    }
}