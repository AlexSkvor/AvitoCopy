package com.example.devapi.controllers.search

/**
 * Created on 6/22/2019
 * @author Alexey Ivanov
 * */

object ResellersFilter {

    data class CarComparable (
            val mark: String,
            val model: String,
            val year: Int,
            val color: String,
            val driveUnit: String,
            val bodyType: String,
            val steeringSide: String,
            val mileage: Int
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
            val currentCar = carsMap[comparableFromCar(it)]
            if (currentCar == null || currentCar.price > it.price)
                carsMap[comparableFromCar(it)] = it
        }
        return carsMap.values.toList()
    }
}