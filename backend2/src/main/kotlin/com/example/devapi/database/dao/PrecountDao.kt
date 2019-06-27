package com.example.devapi.database.dao

import com.example.devapi.database.entities.CarEntity
import com.example.devapi.database.entities.PrecountEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

interface PrecountDao : CrudRepository<PrecountEntity, String> {

    fun getByFullName(fullName: String): PrecountEntity?

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query(value = "update PrecountEntity c set c.number = :newNumber, c.medianPrice = :newPrice where c.fullName = :full")
    fun updatePriceAndNumber(full: String, newNumber: Int, newPrice: Int)
}

fun PrecountDao.updateWith(car: CarEntity) {
    if (car.tradeMark.isBlank() && car.model.isBlank() && car.year == -1) return
    val old = getByFullName(car.tradeMark + car.model + car.year)
    if (old == null) {
        val newPrecount = PrecountEntity(
                fullName = car.tradeMark + car.model + car.year,
                mark = car.tradeMark,
                model = car.model,
                year = car.year,
                number = 1,
                medianPrice = car.price
        )
        save(newPrecount)
    } else {
        val oldPrice = old.medianPrice * old.number
        val newPrice = (oldPrice + car.price) / (old.number + 1)
        updatePriceAndNumber(full = old.fullName, newNumber = old.number + 1, newPrice = newPrice)
    }
}

fun PrecountDao.getPrice(car: CarEntity): Int {
    if (car.tradeMark.isBlank() && car.model.isBlank() && car.year == -1) return -1
    val entity = getByFullName(car.tradeMark + car.model + car.year)
    return entity?.medianPrice ?: -1
}