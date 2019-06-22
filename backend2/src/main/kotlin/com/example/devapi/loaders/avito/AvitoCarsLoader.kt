package com.example.devapi.loaders.avito

import com.example.devapi.database.dao.*
import com.example.devapi.database.entities.CarEntity
import com.example.devapi.extensions.alsoPrintDebug
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.lang.Exception
import java.util.*
import kotlin.random.Random

/**
 * Created on 6/20/2019
 * @author AlexSkvor
 * */

@Component
class AvitoCarsLoader(
        private val carsRepository: CarsDao,
        private val linksRepository: LinksDao
) {

    @Scheduled(fixedRate = 5000)
    fun loadCars() {
        randomWait()
        if (linksRepository.empty) return

        val link = linksRepository.nextAvitoLink()
        link.alsoPrintDebug("AAAAAAAAA")
        val car = carFromLink(link.url, link.city)
        if (car != null) {
            carsRepository.insertReplace(car)
            linksRepository.replace(link.copy(lastCheck = Date(), loaded = true))
        } else {
            alsoPrintDebug("NULL")
            carsRepository.deleteById(link.url)
            linksRepository.deleteById(link.url)
        }
    }

    private fun carFromLink(link: String, city: String): CarEntity? {
        return try {
            val page = Jsoup.connect(link).get()
            AvitoMapper.fromMap(
                    getMainProperties(page) + getAdvanced(page),
                    getPrice(page),
                    getImage(page),
                    getComment(page),
                    link,
                    city,
                    getPlacementDate(page))
        } catch (e: Exception) {
            e.alsoPrintDebug("Error happened")
            null
        }
    }

    private fun getMainProperties(page: Document): Map<String, String> =
            page.body().getElementsByClass("item-params-list-item").map {
                it.getElementsByClass("item-params-label").first().text() to it.textNodes()[1].toString()
            }.toMap()

    private fun getImage(page: Document) = page.toString().substringAfter("avito.item.image = ")
            .substringBefore(";").replace("'", "")

    private fun getPrice(page: Document): String =
            page.toString().substringAfter("avito.item.price").substring(0, 20).filter { it in '0'..'9' }

    private fun getComment(page: Document): String =
            page.body().getElementsByClass("item-description").text()

    private fun getAdvanced(page: Document): Map<String, String> =
            page.body().getElementsByClass("advanced-params-param").toList().map {
                it.getElementsByClass("advanced-params-param-title").text() to it
                        .getElementsByClass("advanced-params-param-item")
                        .toList().map { attr -> attr.text() }
            }.map { it.first to stringFormat(it.second) }.toMap()

    private fun getPlacementDate(page: Document): Date {
        val str = page.body()
                .getElementsByClass("title-info-metadata-item-redesign")
                .text()

        return AvitoPlacementDateParser.dateFromRussianString(str)
    }

    private fun randomWait() {
        try {
            Thread.sleep(Random.nextLong(1, 10000))
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun stringFormat(list: List<String>): String =
            if (list.size == 1) list.first()
            else {
                var value = ""
                list.forEach { value += "$it; " }
                value
            }
}