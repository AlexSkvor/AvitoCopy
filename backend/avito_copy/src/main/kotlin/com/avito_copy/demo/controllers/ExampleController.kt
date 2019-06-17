package com.avito_copy.demo.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created on 6/17/2019
 * @author AlexSkvor
 * */

@Controller
class ExampleController {
    @RequestMapping("/")
    @ResponseBody
    fun hello() = "Hello world mufucka"
}