package com.example.devapi.loaders.youla

import com.example.devapi.database.dao.*
import com.example.devapi.database.entities.CarEntity
import com.example.devapi.utils.alsoPrintDebug
import com.example.devapi.utils.loggedTry
import com.example.devapi.utils.stringFormat
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*
import kotlin.random.Random

@Component
class YoulaCarsLoader(
        private val carsRepository: CarsDao,
        private val linksRepository: LinksDao
) {

    @Scheduled(fixedRate = 3000, initialDelay = 0)
    fun loadCars() {
        randomWait()
        if (linksRepository.empty) return

        val link = linksRepository.nextYoulaLink()
        val car = carFromLink(link.url, link.city)
        if (car != null) {
            loggedTry {
                car.alsoPrintDebug("AAAAAAAAAAAA")
                /*carsRepository.insertReplace(car)
                linksRepository.replace(link.copy(lastCheck = Date(), loaded = true))*/
            }
        } else {
            if (carsRepository.existsById(link.url))
                carsRepository.deleteById(link.url)
            linksRepository.deleteById(link.url)
        }
    }

    private fun carFromLink(link: String, city: String): CarEntity? {
        return try {
            val page = Jsoup.connect(link).get()
            YoulaMapper.fromMap(
                    getBrandModelGeneration(page) +
                            getPrice(page) +
                            getMainCharacteristics(page) +
                            getComment(page) +
                            getMore(page),
                    original = link,
                    city = city,
                    photo = getPhoto(page),
                    creationTime = creationTime(page)
            )

        } catch (e: Exception) {
            e.printStackTrace()
            e.alsoPrintDebug("Error happened")
            null
        }
    }

    private fun creationTime(page: Document): Date {
        val str = page.body().getElementsByClass("SellerInfo_infoRow__1rFTA")
                .text()?.alsoPrintDebug("Date probably") ?: ""
        return YoulaPlacementDateParser.parseDate(str)
    }

    private fun getPhoto(page: Document): String =
            page.body().getElementsByClass("PhotoGallery_photoWrapper__3m7yM")
                    .toString().substringAfter("img src=\"").substringBefore("\"")

    private fun getBrandModelGeneration(page: Document): Map<String, String> {
        fun get(attr: String): String = page.body()
                .getElementsByClass("Breadcrumbs_item__ixZgR")
                .firstOrNull { it.attr("data-type") == attr }
                ?.text() ?: ""

        return mapOf("Модель" to get("model"),
                "Марка" to get("brand"),
                "Поколение" to get("generation"))
    }

    private fun getPrice(page: Document): Map<String, String> = mapOf("Цена" to
            page.body().getElementsByClass("rouble AdvertCardStickyContacts_price__lF-zV AdvertCardStickyContacts_fontStyles__2Lpg9")
                    .text())

    private fun getMainCharacteristics(page: Document): Map<String, String> =
            page.body().getElementsByClass("AdvertSpecs_row__ljPcX")
                    .toList()
                    .map { it.getElementsByClass("AdvertSpecs_label__2JHnS") to it.getElementsByClass("AdvertSpecs_data__xK2Qx") }
                    .map { it.first.first() to it.second.first() }
                    .map { it.first.text() to it.second.text() }
                    .toMap()

    private fun getComment(page: Document): Map<String, String> = mapOf("Комментарий" to
            page.body().getElementsByClass("AdvertCard_descriptionInner__KnuRi").text())

    private fun getMore(page: Document): Map<String, String> =
            page.body().getElementsByClass("AdvertEquipment_equipmentSection__3YpK5")
                    .toList()
                    .map {
                        it.getElementsByClass("h4 AdvertEquipment_title__GSqVx").text() to
                                it.getElementsByClass("AdvertEquipment_equipmentItem__Jk5c4").toList()
                                        .map { element -> element.text() }.stringFormat()
                    }.toMap()

    private fun randomWait() {
        try {
            Thread.sleep(Random.nextLong(1, 10000))
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}