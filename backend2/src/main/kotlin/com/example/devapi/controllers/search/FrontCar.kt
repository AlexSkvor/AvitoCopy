package com.example.devapi.controllers.search

import com.example.devapi.database.entities.CarEntity
import com.example.devapi.utils.frontFormat

/**
 * Created on 6/17/2019
 * @author AlexSkvor
 * */

data class FrontCar(
        var id: Int = 0,
        val originalUrl: String = "",
        val imageUrl: String = "",
        val tradeMark: String = "",
        val model: String = "",
        val color: String = "",
        val driveUnit: String = "",
        val price: Int = -1,
        val medianPrice: Int = -1,
        val year: Int = -1,
        val bodyType: String = "",
        val steeringSide: String = "",
        val mileagePerYear: Int = -1,
        val comment: String,
        val actualizationTime: String,
        val source: String,
        val totalMileage: Int = -1,
        val city: String
) {
    constructor(entity: CarEntity, medianPrice: Int) : this(
            originalUrl = entity.originalUrl,
            imageUrl = entity.imageUrl,
            tradeMark = entity.tradeMark,
            model = entity.model,
            color = entity.color,
            driveUnit = entity.driveUnit,
            price = entity.price,
            year = entity.year,
            bodyType = entity.bodyType,
            steeringSide = entity.steeringSide,
            mileagePerYear = medianMileage(entity.mileage, entity.year),
            comment = entity.comment,
            actualizationTime = entity.actualizationTime.frontFormat(),
            source = entity.source,
            city = entity.city,
            medianPrice = medianPrice,
            totalMileage = entity.mileage
    )

    companion object {
        private fun medianMileage(mileage: Int, year: Int): Int =
                if (year == 2019) mileage
                else mileage / (2019 - year)
    }
}

fun Array<FrontCar>.setIds() {
    var iter = 0
    this.forEach {
        it.id = iter
        iter++
    }
}
