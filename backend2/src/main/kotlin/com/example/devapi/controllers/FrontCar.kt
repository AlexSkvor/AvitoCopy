package com.example.devapi.controllers

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
        if (it.mileage.toLong() / m + danger.toLong() / 100 < 1L) it.dangerouslyLowMileage = true
        if (it.mileage.toLong() / m - danger.toLong() / 100 > 1L) it.dangerouslyHighMileage = true
    }
}

fun Array<FrontCar>.setDangerouslyPriceFlags(danger: Int, middle: Int) {
    val m = if (middle == 0) 1L else middle.toLong()
    this.forEach {
        if (it.price.toLong() / m + danger.toLong() / 100 < 1L) it.dangerouslyLowPrice = true
        if (it.price.toLong() / m - danger.toLong() / 100 > 1L) it.dangerouslyHighPrice = true
    }
}
