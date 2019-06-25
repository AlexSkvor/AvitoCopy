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
        val year: Int = -1,
        val bodyType: String = "",
        val steeringSide: String = "",
        val mileage: Int = -1,
        val comment: String,
        val actualizationTime: String,
        val source: String,
        val city: String,

        var dangerouslyLowPrice: Boolean = false,
        var dangerouslyHighPrice: Boolean = false,
        var dangerouslyHighMileage: Boolean = false,
        var dangerouslyLowMileage: Boolean = false
) {
    constructor(entity: CarEntity) : this(
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
            mileage = entity.mileage,
            comment = entity.comment,
            actualizationTime = entity.actualizationTime.frontFormat(),
            source = entity.source,
            city = entity.city
    )
}

fun Array<FrontCar>.setIds() {
    var iter = 0
    this.forEach {
        it.id = iter
        iter++
    }
}

fun Array<FrontCar>.setDangerouslyMileageFlags(danger: Int, middle: Int) {
    val m = if (middle == 0) 1L else middle.toLong()
    this.forEach {
        if (it.mileage * 100 / m - 100 > danger) it.dangerouslyHighMileage = true
        if (it.mileage * 100 / m - 100 < -danger) it.dangerouslyLowMileage = true
    }
}

fun Array<FrontCar>.setDangerouslyPriceFlags(danger: Int, middle: Int) {
    val m = if (middle == 0) 1L else middle.toLong()
    this.forEach {
        if (it.price * 100 / m - 100 > danger) it.dangerouslyHighPrice = true
        if (it.price * 100 / m - 100 < -danger) it.dangerouslyLowPrice = true
    }
}
