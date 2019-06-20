package com.avito_copy.demo.data_loaders

import com.avito_copy.demo.entities.back.LinkEntity
import com.avito_copy.demo.entities.back.LinksList
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

@Component
class LinksLoader {

    private var pageNum = 0
    private val gson = Gson()

    @Scheduled(fixedRate = 18000)
    fun loadLinks() {
        if (pageNum >= 100) return

        val page = Jsoup.connect("https://www.avito.ru/kaliningrad/avtomobili?p=$pageNum&radius=1000&f=188_0b0.1375_0b0.1374_0b0.1286_0b0").get()
        val elements: Elements = page.body().getElementsByClass("js-item-slider item-slider")
        val links: List<String> = elements.filter { it.hasAttr("href") }
                .map { it.attr("href") }
                .map { "https://www.avito.ru$it" }

        if (links.isEmpty()) return
        val file = File("allLinks.txt")
        if (!file.exists()) {
            file.createNewFile()
            file.writeText("")
            println("File Path: ${file.absolutePath}")
        }

        val old = gson.fromJson(file.readText(), LinksList::class.java)?.list ?: listOf()

        val oldLinks = old.map { it.url }
        val ans = (links.filter { !oldLinks.contains(it) }).map { LinkEntity(it) }

        file.writeText(gson.toJson(LinksList((ans + old).distinct())))

        pageNum++
    }

    @Scheduled(fixedRate = 900_000)
    fun loadNewLinks() {
        val page = Jsoup.connect("https://www.avito.ru/kaliningrad/avtomobili?p=1&radius=1000&f=188_0b0.1375_0b0.1374_0b0.1286_0b0").get()
        val elements: Elements = page.body().getElementsByClass("js-item-slider item-slider")
        val links: List<String> = elements.filter { it.hasAttr("href") }
                .map { it.attr("href") }
                .map { "https://www.avito.ru$it" }

        if (links.isEmpty()) return
        val file = File("allLinks.txt")
        if (!file.exists()) {
            file.createNewFile()
            file.writeText("")
        }

        val old = gson.fromJson(file.readText(), LinksList::class.java)?.list ?: listOf()

        val oldLinks = old.map { it.url }
        val ans = links.filter { !oldLinks.contains(it) }.map { LinkEntity(it) }

        file.writeText(gson.toJson(LinksList((ans + old).distinct())))
    }
}