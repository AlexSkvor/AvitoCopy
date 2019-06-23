package com.example.devapi.database.dao

import com.example.devapi.database.entities.CarEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface CarsDao : CrudRepository<CarEntity, String> {
    fun getAllByYearIsBetweenAndPriceIsBetweenOrderByPrice(minYear: Int, maxYear: Int, minPrice: Int, maxPrice: Int): List<CarEntity>
}

fun CarsDao.insertReplace(car: CarEntity) {
    if (existsById(car.originalUrl)) deleteById(car.originalUrl)
    save(car)
}