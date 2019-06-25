package com.example.devapi.controllers.responses

import com.example.devapi.utils.CODE_SUCCESS
import com.example.devapi.utils.STATUS_SUCCESS

data class AirPlanesResponse<T>(
        val status: String = STATUS_SUCCESS,
        val code: Int = CODE_SUCCESS,
        val data: List<T> = listOf(),
        val message: String = ""
)