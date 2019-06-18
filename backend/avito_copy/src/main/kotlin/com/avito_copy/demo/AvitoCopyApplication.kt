package com.avito_copy.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class AvitoCopyApplication: SpringBootServletInitializer() {

    override fun configure(builder: SpringApplicationBuilder?): SpringApplicationBuilder? {
        return builder?.sources(AvitoCopyApplication::class.java)
    }
}

    fun main(args: Array<String>) {
        runApplication<AvitoCopyApplication>(*args)
    }
