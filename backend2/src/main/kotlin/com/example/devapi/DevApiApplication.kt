package com.example.devapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * Created on 6/21/2019
 * @author AlexSkvor
 * */


@EnableScheduling
@SpringBootApplication
class DevApiApplication : SpringBootServletInitializer() {
    override fun configure(builder: SpringApplicationBuilder?): SpringApplicationBuilder? {
        return builder?.sources(DevApiApplication::class.java)
    }
}

fun main(args: Array<String>) {
    runApplication<DevApiApplication>(*args)
}
