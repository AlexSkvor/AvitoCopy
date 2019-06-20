package com.avito_copy.demo.controllers

import com.avito_copy.demo.entities.front.FrontCar
import com.google.gson.Gson
import java.io.File
import java.lang.Exception

object CarsHelper {

    fun getCars(): List<FrontCar> {
        val carsFile = File("allCars.txt")
        if (!carsFile.exists()) return listOf()
        val cars = mutableListOf<FrontCar>()
        carsFile.forEachLine { cars.add(Gson().fromJson(it, FrontCar::class.java)) }
        return cars
    }

    fun getPossibleMarks(): List<String> {
        return try {
            File("allTradeMarks.txt").readLines()
        } catch (e: Exception) {
            println("Tried to reed trade marks from file $e")
            listOf()
        }
    }

}