package com.example.devapi.loaders

import com.example.devapi.database.dao.CarsDao
import com.example.devapi.database.dao.LinksDao
import com.example.devapi.utils.filesDirectory
import com.google.gson.Gson
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.io.File

@Component
class CitiesMapper(
        private val carsRepository: CarsDao,
        private val linksRepository: LinksDao
) {

    @Scheduled(fixedRate = 600_000)
    fun getCities() {
        val file = File(filesDirectory + "cities.txt")
        val nnn = CityMap(Gson().fromJson(file.readText(), CityMap::class.java)
                .data
                .filter { it.key != it.value })
        nnn.data.forEach {
            carsRepository.replaceCity(it.key, it.value)
            linksRepository.replaceCity(it.key, it.value)
        }
        file.writeText(Gson().toJson(nnn))
    }
}

data class CityMap(
        val data: Map<String, String>
)