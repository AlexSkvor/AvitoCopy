package com.example.devapi.database.dao

import com.example.devapi.database.entities.PopularCarEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

interface PopularCarsDao : CrudRepository<PopularCarEntity, String> {

    @Query(value = "SELECT * FROM CITY_INFO WHERE LOADED = false", nativeQuery = true)
    fun getNonLoadedList(): List<PopularCarEntity>

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query(value = "update PopularCarEntity c set c.loaded = true where c.fullName = :key")
    fun markLoaded(key: String)

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query(value = "update PopularCarEntity c set c.loaded = false where c.model =''")
    fun startReload(key: String)
}

fun PopularCarsDao.getNext(): PopularCarEntity? {
    return getNonLoadedList().firstOrNull()
}

fun PopularCarsDao.replaceAll(list: List<PopularCarEntity>) {
    list.forEach { if (existsById(it.fullName)) deleteById(it.fullName) }
    saveAll(list)
}