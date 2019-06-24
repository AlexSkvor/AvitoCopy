@file:Suppress("SqlNoDataSourceInspection", "SqlDialectInspection")

package com.example.devapi.database.dao

import com.example.devapi.database.entities.CarEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

interface CarsDao : CrudRepository<CarEntity, String> {
    fun getAllByYearIsBetweenAndPriceIsBetweenOrderByPrice(minYear: Int, maxYear: Int, minPrice: Int, maxPrice: Int): List<CarEntity>

    fun countBySource(source: String): Int

    @Query(value = "SELECT DISTINCT TRADE_MARK FROM CARS", nativeQuery = true)
    fun getTradeMarks(): List<String>

    @Query(value = "SELECT DISTINCT COLOR FROM CARS", nativeQuery = true)
    fun getColors(): List<String>

    @Query(value = "SELECT DISTINCT BODY_TYPE FROM CARS", nativeQuery = true)
    fun getBodyTypes(): List<String>

    @Query(value = "SELECT DISTINCT MODEL FROM CARS WHERE TRADE_MARK = :mark", nativeQuery = true)
    fun getModelsWithTradeMark(mark: String): List<String>

    @Query(value = "SELECT DISTINCT SOURCE FROM CARS", nativeQuery = true)
    fun getSources(): List<String>

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Modifying
    @Query(value = "update CarEntity c set c.city = :newValue where c.city = :old")
    fun replaceCity(old: String, newValue: String)
}

fun CarsDao.insertReplace(car: CarEntity) {
    if (existsById(car.originalUrl)) deleteById(car.originalUrl)
    save(car)
}