package com.example.devapi.responses

/**
 * Created on 6/17/2019
 * @author AlexSkvor
 * */

class BaseResponse<T>(val status: String,
                      val code: Int,
                      val data: Array<T>,
                      val message: String = "",
                      val medianCost: Int = 0,
                      val medianMileage: Int = 0) {
    val size: Int
        get() = data.size
}