package com.avito_copy.demo.entities.front

/**
 * Created on 6/17/2019
 * @author AlexSkvor
 * */

data class FrontCar(
        val id: String,
        val imageUrl: String = "",
        val tradeMark: String = "",
        val model: String = "",
        val color: String = "",
        val driveUnit: String = "",
        val price: String = "",
        val year: String = "",
        val bodyType: String = "",
        val steeringSide: String = "",
        val mileage: String = ""
) {
}