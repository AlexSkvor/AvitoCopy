package com.example.devapi.controllers.info

import com.example.devapi.utils.CODE_SUCCESS
import com.example.devapi.utils.STATUS_SUCCESS
import com.example.devapi.database.dao.CarsDao
import com.example.devapi.database.dao.LinksDao
import com.example.devapi.responses.BaseResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created on 6/20/2019
 * @author AlexSkvor
 * */

@RestController
@RequestMapping("/info")
class InfoController(
        private val carsRepository: CarsDao,
        private val linksRepository: LinksDao
) {

    @GetMapping("/cars/count")
    fun getTotalMarksNumber(): BaseResponse<Int> {
        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, arrayOf(carsRepository.count().toInt()))
    }

    @GetMapping("/cars/cities")
    fun getCities(@RequestParam(value = "all", required = false, defaultValue = "false") all: String): BaseResponse<String> {
        val ans = if (all == "true") linksRepository.getAllCities()
        else linksRepository.getLoadedCities()
        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, ans.toTypedArray())
    }

    /*@GetMapping("/cars/tradeMarks")
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

    @GetMapping("/cars/models")
    fun getModelsList(
            @RequestParam(value = "tradeMarks", required = false, defaultValue = "") tradeMarks: Array<String>
    ): BaseResponse<String> {
        ValidationHelper.validateTradeMarks(tradeMarks)?.let { return errorResponse(it) }
        if (tradeMarks.isEmpty()) return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, getPossibleModels().list.map { it.models }.merge().toTypedArray())
        val data = getPossibleModels().list.filter { tradeMarks.contains(it.tradeMark) }.map { it.models }.merge().toTypedArray()
        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, data)
    }*/
}