package com.avito_copy.demo.data_loaders

import com.avito_copy.demo.entities.back.LinksList
import com.avito_copy.demo.entities.front.FrontCar
import com.avito_copy.demo.entities.front.FrontCarMapper
import com.google.gson.Gson
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

/**
 * Created on 6/20/2019
 * @author AlexSkvor
 * */

@Component
class CarsLoader {

    private var num = 0
    private val gson = Gson()
    private val time: String
        get() = SimpleDateFormat("MM_DD_HH_mm_ss").format(Date())

    @Scheduled(fixedRate = 5000)
    fun loadCars() {
        rescueFiles()
        try {
            Thread.sleep(Random.nextLong(1, 10000))
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        val file = File("allLinks.txt")
        if (!file.exists()) return
        val links = gson.fromJson(file.readText(), LinksList::class.java)
        val link = links.list.asSequence().firstOrNull { !it.loaded && !it.errored }
        link?.let {
            val car = carFromLink(link.url)
            car?.let {
                val carsFile = File("allCars.txt")
                if (!carsFile.exists()) carsFile.createNewFile()
                carsFile.appendText(gson.toJson(car) + "\n")
                links.list.forEach { if (it.url == link.url) it.loaded = true }
                file.writeText(gson.toJson(links))
            }
            if (car == null) {
                links.list.forEach { if (it.url == link.url) it.errored = true }
                file.writeText(gson.toJson(links))
            }
        }
    }

    private fun carFromLink(link: String): FrontCar? {
        try {
            val page = Jsoup.connect(link).get()
            val itemParams: Elements = page.body().getElementsByClass("item-params-list-item")
            val valuesList: Map<String, String> =
                    itemParams.map {
                        it.getElementsByClass("item-params-label").first().text() to it.textNodes()[1].toString()
                    }.toMap()

            val price = page.toString().substringAfter("avito.item.price").substring(0, 20).filter { it in '0'..'9' }
            val image = page.toString().substringAfter("avito.item.image = ")
                    .substringBefore(";").replace("'", "")

            val comment = page.body().getElementsByClass("item-description").text()

            val advanced: Map<String, List<String>> = page.body().getElementsByClass("advanced-params-param").toList()
                    .map {
                        it.getElementsByClass("advanced-params-param-title").text() to it.getElementsByClass("advanced-params-param-item")
                                .toList().map { attr -> attr.text() }
                    }.toMap()

            return FrontCarMapper.fromMap(valuesList, price, image, comment, advanced, link)
        } catch (e: Exception) {
            println(e)
            return null
        }
    }

    private fun rescueFiles() {
        val cars = File("allCars.txt")
        val saveCars = File("allCarsSaving$time")

        val links = File("allLinks.txt")
        val savedLinks = File("allLinksSaving$time")

        if (num % 99 == 0) {
            cars.copyTo(saveCars)
            links.copyTo(savedLinks)
        }
        if (num == 100_020) num = 0
        num++
    }
}