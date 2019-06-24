package com.example.devapi.database

import com.example.devapi.database.dao.CarsDao
import com.example.devapi.database.dao.LinksDao
import com.example.devapi.database.entities.Dumps
import com.example.devapi.controllers.responses.BaseResponse
import com.example.devapi.utils.*
import com.google.gson.Gson
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.lang.Exception
import java.util.*

/**
 * Created on 6/23/2019
 * @author AlexSkvor
 * */

@RestController
@RequestMapping("/dump")
class DumpController(
        private val linksRepository: LinksDao,
        private val carsRepository: CarsDao
) {

    @GetMapping("/please")
    fun getTotalMarksNumber(): BaseResponse<String> {
        return try {
            BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, arrayOf("true ${dump()}"))
        } catch (e: Exception) {
            BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, arrayOf("$e"))
        }
    }

    @Scheduled(fixedRate = 10_800_000)//3 hours
    fun dumpRegular() {
        dump()
    }

    private fun dump(): Int {
        val links = linksRepository.findAll().toList()
        val cars = carsRepository.findAll().toList()
        val dump = Dumps(cars, links)
        val file = File("$filesDirectory${Date().time}$fileDump")
        file.writeText(Gson().toJson(dump))
        return cars.size
    }

}