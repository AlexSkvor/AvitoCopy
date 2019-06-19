package com.avito_copy.demo.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import com.avito_copy.demo.responses.BaseResponse
import com.avito_copy.demo.entities.front.FrontCar
import com.avito_copy.demo.entities.front.FrontCarGenerator

@Controller
class TestGeneratedCarController {

    private companion object {
        const val STATUS_SUCCESS = "success"
        const val STATUS_ERROR = "error"
        const val CODE_SUCCESS = 200
        const val CODE_AUTH_FAILURE = 401
        const val BAD_REQUEST = 400

    }

    @RequestMapping("/generatedCars")
    @ResponseBody
    fun responce() = BaseResponse<FrontCar> (
            status = STATUS_SUCCESS,
            code = CODE_SUCCESS,
            data = FrontCarGenerator.getFrontCarsList()
    )
}