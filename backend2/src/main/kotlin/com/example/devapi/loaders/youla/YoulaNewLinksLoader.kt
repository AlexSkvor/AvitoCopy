package com.example.devapi.loaders.youla

import com.example.devapi.database.dao.LinksDao
import com.example.devapi.database.entities.LinkEntity
import com.example.devapi.utils.youla
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@Component
class YoulaNewLinksLoader(private val repository: LinksDao) {

    private var pageNum = 8

    @Scheduled(fixedRate = 27000)
    fun loadLinks() {
        if (pageNum < 1) return
        saveLinks(pageNum)
        pageNum--
    }

    @Scheduled(fixedRate = 900_000)
    fun loadNewLinks() {
        saveLinks(1)
    }

    private fun saveLinks(i: Int) {
        val links = getLinks(i).map { LinkEntity(it.first, Date(), loaded = false, source = youla, city = it.second) }
        val filtered = links.filter { !repository.existsById(it.url) }
        repository.saveAll(filtered)
    }

    private fun getLinks(pageNumber: Int = 1): List<Pair<String, String>> {
        val page = Jsoup.connect("https://auto.youla.ru/kaliningradskaya-oblast/cars/new/?photo=0&page=$pageNumber").get()
        val elements: Elements = page.body().getElementsByClass("SerpSnippet_snippet__3O1t2 app_roundedBlockWithShadow__1rh6w")

        return elements.map {
            linkFromElement(it) to cityFromElement(it)
        }.distinct()
    }

    private fun linkFromElement(element: Element): String =
            element.getElementsByClass("SerpSnippet_colLeft__1qO8G")
                    .first()
                    .toString()
                    .substringAfter("href=\"")
                    .substringBefore("\"")


    private fun cityFromElement(element: Element): String =
            element.getElementsByClass("blackLink SerpSnippet_address__DKug3")
                    .text().substringBefore(',', "Не определено")

}