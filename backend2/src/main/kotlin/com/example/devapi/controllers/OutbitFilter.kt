package com.example.devapi.controllers

import com.example.devapi.entities.front.FrontCar

/**
 * Created on 6/22/2019
 * @author Alexey Ivanov
 * */

object OutbitFilter {

    data class CarComparable (
            val mark: String,
            val model: String,
            val year: String,
            val color: String,
            val driveUnit: String,
            val bodyType: String,
            val steeringSide: String,
            val mileage: String
    )

    private fun comparableFromCar(car: FrontCar) : CarComparable {
        return CarComparable(
                mark = car.tradeMark,
                model = car.model,
                year = car.year,
                color = car.color,
                driveUnit = car.driveUnit,
                bodyType = car.bodyType,
                steeringSide = car.steeringSide,
                mileage = car.mileage
        )
    }

    fun filter(cars: List<FrontCar>): List<FrontCar> {
        val carsMap = mutableMapOf<CarComparable, FrontCar>()
        cars.forEach {
            val currentCar = carsMap.get(comparableFromCar(it))
            if (currentCar == null || currentCar.price > it.price)
                carsMap.put(comparableFromCar(it), it)
        }
        return carsMap.values.toList()
    }
}