package com.example.devapi.entities.front

/**
 * Created on 6/17/2019
 * @author AlexSkvor
 * */

data class FrontCar(
        var id: String = "0",
        val originalUrl: String = "",
        val imageUrl: String = "",
        val tradeMark: String = "",
        val model: String = "",
        val color: String = "",
        val driveUnit: String = "",
        val price: String = "",
        val year: String = "",
        val bodyType: String = "",
        val steeringSide: String = "",
        val mileage: String = "",
        val additionalInfo: AdditionalCarInfo = AdditionalCarInfo(),
        val advancedInfo: AdvancedInfo = AdvancedInfo()
) {
}