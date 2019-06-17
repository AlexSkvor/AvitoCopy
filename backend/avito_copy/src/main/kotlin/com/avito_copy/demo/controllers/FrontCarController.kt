package com.avito_copy.demo.controllers

import com.avito_copy.demo.entities.front.FrontCar
import com.avito_copy.demo.entities.front.FrontCarGenerator
import com.avito_copy.demo.responses.BaseResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created on 6/17/2019
 * @author AlexSkvor
 * */

@RestController
@RequestMapping("/justCar")
class FrontCarController {

    private companion object {
        const val STATUS_SUCCESS = "success"
        const val STATUS_ERROR = "error"
        const val CODE_SUCCESS = 200
        const val CODE_AUTH_FAILURE = 401


    }

    @GetMapping
    fun showStatus(): BaseResponse<String> =
            BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, arrayOf())

    /*@GetMapping("/filtered")
    fun filtered(@RequestParam(value = "filterType") filterType: String): BaseResponse<FrontCar> {

    }*/

    @GetMapping("/allCars")
    fun filtered(): BaseResponse<FrontCar> {
        val data = FrontCarGenerator.getFrontCarsList(3)
        return BaseResponse(STATUS_SUCCESS, CODE_SUCCESS, data.toTypedArray())
    }


}