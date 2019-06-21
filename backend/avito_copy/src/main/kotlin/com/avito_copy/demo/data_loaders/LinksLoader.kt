package com.avito_copy.demo.data_loaders

import com.avito_copy.demo.entities.back.LinkEntity
import com.avito_copy.demo.entities.back.LinksList
import com.avito_copy.demo.fileAllLinks
import com.google.gson.Gson
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.io.File

/**
 * Created on 6/20/2019
 * @author AlexSkvor
 * */

/*@Component
class LinksLoader {

    private var pageNum = 0
    private val gson = Gson()

    @Scheduled(fixedRate = 18000)
    fun loadLinks() {
        if (pageNum >= 100) return
        saveLinks(pageNum)
        pageNum++
    }

    @Scheduled(fixedRate = 900_000)
    fun loadNewLinks() {
        saveLinks(1)
    }

    fun saveLinks(i: Int) {
        val links = getLinks(i)
        if (links.isEmpty()) return

        val file = File(fileAllLinks).saveCreation()
        val old = gson.fromJson(file.readText(), LinksList::class.java)?.list ?: listOf()
        val oldLinks = old.map { it.url }
        val ans = links.filter { !oldLinks.contains(it) }.map { LinkEntity(it) }

        file.writeText(gson.toJson(LinksList((ans + old).distinct())))
    }

    private fun getLinks(pageNumber: Int = 1): List<String> {
        val page = Jsoup.connect("https://www.avito.ru/kaliningrad/avtomobili?p=$pageNumber&radius=1000&f=188_0b0.1375_0b0.1374_0b0.1286_0b0").get()
        val elements: Elements = page.body().getElementsByClass("js-item-slider item-slider")

        return elements.filter { it.hasAttr("href") }
                .map { it.attr("href") }
                .map { "https://www.avito.ru$it" }
    }

    private fun File.saveCreation(): File {
        if (!this.exists()) {
            this.createNewFile()
            this.writeText("")
        }
        return this
    }
}*/