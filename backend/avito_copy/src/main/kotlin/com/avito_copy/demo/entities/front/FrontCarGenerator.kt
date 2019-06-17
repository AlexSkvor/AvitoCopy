package com.avito_copy.demo.entities.front

import java.util.*
import kotlin.random.Random

/**
 * Created on 6/17/2019
 * @author AlexSkvor
 * */

//TODO just for tests needs to be removed later
object FrontCarGenerator {

    fun getFrontCarsList(number: Int = 20): List<FrontCar> {
        val list = mutableListOf<FrontCar>()
        for (i in 1..number) {
            val car = FrontCar(
                    id = UUID.randomUUID().toString(),
                    imageUrl = "it is url, believe me",
                    tradeMark = "Audi",
                    color = Random.nextInt(100, 120).toString()
            )
            list.add(car)
        }
        return list
    }
}