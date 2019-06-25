package com.example.devapi.utils

import java.io.File

class ArgumentException(private val messageToUser: String) : IllegalArgumentException(messageToUser) {
    init {
        File(fileErrorUser).appendText("\n$messageToUser")
    }
}