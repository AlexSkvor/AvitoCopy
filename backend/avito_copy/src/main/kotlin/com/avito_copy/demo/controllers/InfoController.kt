package com.avito_copy.demo.controllers

import com.avito_copy.demo.controllers.CarsHelper.getCars
import com.avito_copy.demo.controllers.CarsHelper.getPossibleBodyTypes
import com.avito_copy.demo.controllers.CarsHelper.getPossibleColors
import com.avito_copy.demo.controllers.CarsHelper.getPossibleMarks
import com.avito_copy.demo.controllers.CarsHelper.getPossibleSorts
import com.avito_copy.demo.responses.BaseResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created on 6/20/2019
 * @author AlexSkvor
 * */

@RestController
@RequestMapping("/info")
class InfoController {

    @GetMapping("/cars/count")
    fun getTotalMarksNumber(): BaseResponse<Int> {
        val carsNumber = getCars().size
        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, arrayOf(carsNumber))
    }

    @GetMapping("/cars/tradeMarks")
    fun getTradeMarksList(): BaseResponse<String> {
        val data = getPossibleMarks().toTypedArray()
        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, data)
    }

    @GetMapping("/cars/colors")
    fun getColorsList(): BaseResponse<String> {
        val data = getPossibleColors().toTypedArray()
        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, data)
    }

    @GetMapping("/cars/bodyTypes")
    fun getBodyTypesList(): BaseResponse<String> {
        val data = getPossibleBodyTypes().toTypedArray()
        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, data)
    }

    @GetMapping("/cars/sorts")
    fun getSortsList(): BaseResponse<String> {
        val data = getPossibleSorts().toTypedArray()
        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, data)
    }
}