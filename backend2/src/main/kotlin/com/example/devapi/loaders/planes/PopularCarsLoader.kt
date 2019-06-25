package com.example.devapi.loaders.planes

import com.example.devapi.database.dao.PopularCarsDao
import com.example.devapi.database.dao.getNext
import com.example.devapi.database.dao.replaceAll
import com.example.devapi.database.entities.PopularCarEntity
import com.example.devapi.utils.filterDigits
import com.example.devapi.utils.loggedTry
import com.example.devapi.utils.toIntOrZero
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class PopularCarsLoader(
        private val popularRepository: PopularCarsDao
) {

    @Scheduled(fixedDelay = 5_000)
    fun loadInfo() {
        randomWait()
        popularRepository.getNext()?.let { old ->
            loggedTry {
                val url = "https://www.avito.ru/${old.city}/avtomobili/${old.mark}?radius=0"
                popularRepository.markLoaded(old.fullName)

                val page = Jsoup.connect(url).get()
                val models = page.body().getElementsByClass("popular-rubricator-row-1iFic")
                val cars = models.removeGarbage().toCars(old)

                popularRepository.replaceAll(cars)
            }
        }
    }

    private fun Elements.removeGarbage(): List<Pair<String, Int>> {
        val withGarbage = this.map { it.toString() }
                .map { it.substringAfter("data-marker=\"popular-rubricator/link\">") }
                .map { it.substringBefore("</span>") }

        return withGarbage.map {
            it.substringBefore("</a>") to it
                    .substringAfter("\"popular-rubricator/count\">")
                    .filterDigits()
                    .toIntOrZero()
        }
    }

    private fun List<Pair<String, Int>>.toCars(old: PopularCarEntity): List<PopularCarEntity> {
        return this.map { (model: String, number: Int) ->
            PopularCarEntity(
                    fullName = old.city + old.mark + model,
                    city = old.city,
                    mark = old.mark,
                    model = model,
                    number = number,
                    loaded = true
            )
        }
    }

    private fun randomWait() {
        try {
            Thread.sleep(Random.nextLong(1, 10000))
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}