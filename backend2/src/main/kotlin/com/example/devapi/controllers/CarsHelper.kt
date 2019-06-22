package com.example.devapi.controllers

import com.example.devapi.*
import com.example.devapi.entities.front.FrontCar
import com.example.devapi.loaders.*
import com.example.devapi.responses.BaseResponse
import com.google.gson.Gson
import java.io.File
import java.lang.Exception

object CarsHelper {

    /*private val gson = Gson()

    fun getCars(): List<FrontCar> {
        val carsFile = File(fileAllCars)
        if (!carsFile.exists()) return listOf()
        val cars = mutableListOf<FrontCar>()
        carsFile.forEachLine { cars.add(Gson().fromJson(it, FrontCar::class.java)) }
        return cars
    }

    fun getPossibleMarks(): List<String> {
        return try {
            File(fileAllTrademarks).readLines()
        } catch (e: Exception) {
            println("Tried to reed trade marks from file $e")
            listOf()
        }
    }

    fun getPossibleColors(): List<String> {
        return try {
            File(fileAllColors).readLines()
        } catch (e: Exception) {
            println("Tried to reed colors from file $e")
            listOf()
        }
    }

    fun getPossibleBodyTypes(): List<String> {
        return try {
            File(fileAllBodyTypes).readLines()
        } catch (e: Exception) {
            println("Tried to reed body types from file $e")
            listOf()
        }
    }

    fun getPossibleModels(): MarksModelsList {
        return try {
            gson.fromJson(File(fileAllModels).readText(), MarksModelsList::class.java)
        } catch (e: Exception) {
            println("Tried to reed body types from file $e")
            MarksModelsList()
        }
    }

    fun getPossibleSorts(): List<String> {
        return try {
            File(fileAllSorts).readLines()
        } catch (e: Exception) {
            println("Tried to reed sorts from file $e")
            listOf()
        }
    }

    inline fun <reified T> errorResponse(msg: String): BaseResponse<T> =
            BaseResponse(STATUS_ERROR, CODE_BAD_REQUEST, arrayOf(), message = msg)*/
}