package com.avito_copy.demo.responses

/**
 * Created on 6/17/2019
 * @author AlexSkvor
 * */

class BaseResponse<T>(status: String, code: Int, data: Array<T>, size: Int = 0) {

    var status: String = status
        private set

    var code: Int = code
        private set

    var data: Array<T> = data
        private set

    val size: Int
        get() = data.size
}