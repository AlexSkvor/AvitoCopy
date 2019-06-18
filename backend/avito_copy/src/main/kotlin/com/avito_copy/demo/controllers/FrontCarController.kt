package com.avito_copy.demo.controllers

import com.avito_copy.demo.responses.BaseResponse
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created on 6/17/2019
 * @author AlexSkvor
 * */

@RestController
@RequestMapping("/justCar")
class FrontCarController {

    private companion object {
        const val STATUS_SUCCESS = "success"
        const val STATUS_ERROR = "error"
        const val CODE_SUCCESS = 200
        const val CODE_AUTH_FAILURE = 401
        const val BAD_REQUEST = 400

    }

    @GetMapping("/avito")
    fun getAvitoCarsList(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") pageNumber: Int,
            @RequestParam(value = "radius", required = false, defaultValue = "0") radius: Int): BaseResponse<String> {
        checkPageNumber(pageNumber)?.let { return it }
        checkRadius(radius)?.let { return it }
        val data = mutableListOf<String>()
        val page = Jsoup.connect("https://www.avito.ru/kaliningrad/avtomobili?p=$pageNumber&radius=$radius&f=188_0b0.1375_0b0.1374_0b0.1286_0b0").get()
        val elements: Elements = page.body().getElementsByClass("js-item-slider item-slider")
        val links: List<String> = elements.filter { it.hasAttr("href") }.map { it.attr("href") }
        data.addAll(links)
        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, data.toTypedArray())
    }

    private fun checkPageNumber(number: Int): BaseResponse<String>? {
        return if (number in 1..100) null
        else BaseResponse(STATUS_ERROR, BAD_REQUEST, arrayOf("Page Number should be between 1 and 100 inclusive, you passed $number"))
    }

    private fun checkRadius(radius: Int): BaseResponse<String>? {
        val good = listOf(0, 100, 200, 300, 400, 500, 1000)
        return if (good.contains(radius)) null
        else BaseResponse(STATUS_ERROR, BAD_REQUEST, arrayOf("Radius should be one of $good, you passed: $radius"))
    }

}