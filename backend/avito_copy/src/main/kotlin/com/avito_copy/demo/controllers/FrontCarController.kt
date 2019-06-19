package com.avito_copy.demo.controllers

import com.avito_copy.demo.entities.front.FrontCar
import com.avito_copy.demo.entities.front.FrontCarMapper
import com.avito_copy.demo.responses.BaseResponse
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.springframework.web.bind.annotation.*

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
        const val BAD_REQUEST = 400
    }

    @GetMapping("/avito")
    @ResponseBody
    fun getAvitoCarsList(
            @RequestParam(value = "skip", required = false, defaultValue = "0") skip: Int,
            @RequestParam(value = "radius", required = false, defaultValue = "0") radius: Int): BaseResponse<FrontCar> {
        val take = skip + 3
        if (take > 40) return BaseResponse(STATUS_ERROR, BAD_REQUEST, data = arrayOf(),
                message = "you skipped too much!")
        val pageNumber = getPageNumber(skip, take)
        checkPageNumber(pageNumber)?.let { return it }
        checkRadius(radius)?.let { return it }
        val page = Jsoup.connect("https://www.avito.ru/kaliningrad/avtomobili?p=$pageNumber&radius=$radius&f=188_0b0.1375_0b0.1374_0b0.1286_0b0").get()
        val elements: Elements = page.body().getElementsByClass("js-item-slider item-slider")
        val links: List<String> = elements.filter { it.hasAttr("href") }.map { it.attr("href") }

        val data = links.subList(skip, take).map { carFromLink(it) }.toTypedArray()

        for (i in 0 until data.size)
            data[i].id = "$i"

        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, data)
    }

    private fun carFromLink(link: String): FrontCar {
        val url = "https://www.avito.ru$link"
        val page = Jsoup.connect(url).get()
        val itemParams: Elements = page.body().getElementsByClass("item-params-list-item")
        val valuesList: Map<String, String> =
                itemParams.map {
                    it.getElementsByClass("item-params-label").first().text() to it.textNodes()[1].toString()
                }.toMap()//TODO dangerous use try catch here

        val price = page.toString().substringAfter("avito.item.price").substring(0, 20).filter { it in '0'..'9' }
        val image = page.toString().substringAfter("avito.item.image = ")
                .substringBefore(";").replace("'", "")

        val comment = page.body().getElementsByClass("item-description").text()

        val advanced: Map<String, List<String>> = page.body().getElementsByClass("advanced-params-param").toList()
                .map {
                    it.getElementsByClass("advanced-params-param-title").text() to it.getElementsByClass("advanced-params-param-item")
                            .toList().map { attr -> attr.text() }
                }.toMap()

        return FrontCarMapper.fromMap(valuesList, price, image, comment, advanced, url)//TODO convert into car Entity!!!
    }

    private fun checkPageNumber(number: Int): BaseResponse<FrontCar>? {
        return if (number in 1..100) null
        else BaseResponse(STATUS_ERROR, BAD_REQUEST, data = arrayOf(),
                message = "Page Number should be between 1 and 100 inclusive, you passed $number")
    }

    private fun checkRadius(radius: Int): BaseResponse<FrontCar>? {
        val good = listOf(0, 100, 200, 300, 400, 500, 1000)
        return if (good.contains(radius)) null
        else BaseResponse(STATUS_ERROR, BAD_REQUEST, data = arrayOf(),
                message = "Radius should be one of $good, you passed: $radius")
    }

    private fun getPageNumber(skip: Int, take: Int): Int {
        return 1//TODO
    }

}