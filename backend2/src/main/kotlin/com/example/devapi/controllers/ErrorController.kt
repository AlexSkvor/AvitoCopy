package com.example.devapi.controllers

import com.example.devapi.utils.fileErrorUser
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File

@RestController
class IndexController : ErrorController {

    @RequestMapping(value = [PATH])
    fun error(): String {
        val file = File(fileErrorUser)
        val message = file.readLines().last()
        file.appendText("#")
        return if (message.last() == '#') "No error Message"
        else message
    }

    override fun getErrorPath(): String {
        return PATH
    }

    companion object {
        private const val PATH = "/error"
    }
}