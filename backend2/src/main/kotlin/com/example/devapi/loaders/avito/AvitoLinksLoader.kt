package com.example.devapi.loaders.avito

import com.example.devapi.database.dao.LinksDao
import com.example.devapi.database.entities.LinkEntity
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@Component
class AvitoLinksLoader(private val repository: LinksDao) {

    private var pageNum = 100

    @Scheduled(fixedRate = 18000)
    fun loadLinks() {
        if (pageNum < 1) return
        saveLinks(pageNum)
        pageNum--
    }

    @Scheduled(fixedRate = 900_000)
    fun loadNewLinks() {
        saveLinks(1)
    }

    fun saveLinks(i: Int) {
        val links = getLinks(i).map { LinkEntity(it, Date(), loaded = false, source = "Avito", city = getCity(it)) }
        val filtered = links.filter { !repository.existsById(it.url) }
        repository.saveAll(filtered)
    }

    private fun getLinks(pageNumber: Int = 1): List<String> {
        val page = Jsoup.connect("https://www.avito.ru/kaliningradskaya_oblast/avtomobili?p=$pageNumber&f=188_0b0.1375_0b0.1374_0b0.1286_0b0").get()
        val elements: Elements = page.body().getElementsByClass("js-item-slider item-slider")

        return elements.filter { it.hasAttr("href") }
                .map { it.attr("href") }
                .map { "https://www.avito.ru$it" }
                .distinct()
    }

    private fun getCity(url: String): String =
            url.substringAfter("https://www.avito.ru/").substringBefore("/avtomobili")
}