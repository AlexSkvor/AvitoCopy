package com.example.devapi.controllers

import com.example.devapi.extensions.merge
import com.example.devapi.extensions.wrongArguments

object ValidationHelper {

    fun validateTradeMarks(marks: Array<String>): String? {
        if (marks.isEmpty()) return null
        val permitted = CarsHelper.getPossibleMarks()
        val bad = marks.filter { !permitted.contains(it) }
        return if (bad.isEmpty()) null
        else wrongArguments(bad, "<a href = http://84.201.139.189:8080/devApi/info/cars/tradeMarks>Список</a>")
    }

    fun validateColors(colors: Array<String>): String? {
        if (colors.isEmpty()) return null
        val permitted = CarsHelper.getPossibleColors()
        val bad = colors.filter { !permitted.contains(it) }
        return if (bad.isEmpty()) null
        else wrongArguments(bad, "<a href = http://84.201.139.189:8080/devApi/info/cars/colors>Список</a>")
    }

    fun validateBodyTypes(bodies: Array<String>): String? {
        if (bodies.isEmpty()) return null
        val permitted = CarsHelper.getPossibleBodyTypes()
        val bad = bodies.filter { !permitted.contains(it) }
        return if (bad.isEmpty()) null
        else wrongArguments(bad, "<a href = http://84.201.139.189:8080/devApi/info/cars/bodyTypes>Список</a>")
    }

    fun validateModels(marks: Array<String>, models: Array<String>): String? {
        if (models.isEmpty()) return null
        val permitted = CarsHelper.getPossibleModels().list.filter { marks.contains(it.tradeMark) }.map { it.models }.merge()
        val bad = models.filter { !permitted.contains(it) }
        return if (bad.isEmpty()) null
        else wrongArguments(bad, "<a href = http://84.201.139.189:8080/devApi/info/cars/models>Список</a>")
    }

    fun validateSort(sort: String): String? {
        if (sort.isEmpty()) return null
        val permitted = CarsHelper.getPossibleSorts()
        val bad = if (permitted.contains(sort)) null else sort
        return if (bad.isNullOrEmpty()) null
        else wrongArguments(listOf(bad), "<a href = http://84.201.139.189:8080/devApi/info/cars/sorts>Список</a>")
    }

}