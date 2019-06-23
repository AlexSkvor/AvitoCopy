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
    fun getTotalMarksNumber(
            @RequestParam(value = "source", required = false, defaultValue = "") source: String
    ): BaseResponse<Int> =
            if (source.isEmpty()) BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, arrayOf(carsRepository.count().toInt()))
            else BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, arrayOf(carsRepository.countBySource(source)))

    @GetMapping("/cars/cities")
    fun getCities(@RequestParam(value = "all", required = false, defaultValue = "false") all: String): BaseResponse<String> {
        val ans = if (all == "true") linksRepository.getAllCities()
        else linksRepository.getLoadedCities()
        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, ans.toTypedArray())
    }

    @GetMapping("/cars/tradeMarks")
    fun getTradeMarksList(): BaseResponse<String> =
            BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, carsRepository.getTradeMarks().toTypedArray())

    @GetMapping("/cars/colors")
    fun getColorsList(): BaseResponse<String> =
            BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, carsRepository.getColors().toTypedArray())


    @GetMapping("/cars/bodyTypes")
    fun getBodyTypesList(): BaseResponse<String> =
            BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, carsRepository.getBodyTypes().toTypedArray())

    @GetMapping("/cars/sources")
    fun getSources(): BaseResponse<String> =
            BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, carsRepository.getSources().toTypedArray())

    @GetMapping("/cars/sorts")
    fun getSortsList(): BaseResponse<String> {
        val data = arrayOf("Старые", "Новые", "Дешевые", "Дорогие", "Большой_пробег", "Маленький_пробег")
        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, data)
    }

    @GetMapping("/cars/models")
    fun getModelsList(
            @RequestParam(value = "tradeMark", required = true) tradeMark: String
    ): BaseResponse<String> =
            BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, carsRepository.getModelsWithTradeMark(tradeMark).toTypedArray())
}