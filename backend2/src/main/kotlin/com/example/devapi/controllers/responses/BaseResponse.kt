package com.example.devapi.controllers.responses

/**
 * Created on 6/17/2019
 * @author AlexSkvor
 * */

class BaseResponse<T>(val status: String,
                      val code: Int,
                      val data: Array<T>) {
    val size: Int
        get() = data.size
}