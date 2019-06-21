package com.example.devapi.responses

/**
 * Created on 6/17/2019
 * @author AlexSkvor
 * */

class BaseResponse<T>(status: String, code: Int, data: Array<T>, message: String = "", medianCost: Int = 0) {

    var status: String = status
        private set

    var code: Int = code
        private set

    var data: Array<T> = data
        private set

    var message: String = message
        private set

    var medianCost = medianCost
        private set

    val size: Int
        get() = data.size
}