package com.example.devapi.database.dao

import com.example.devapi.database.entities.LinkEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface LinksDao : CrudRepository<LinkEntity, String> {

    @Query("from LinkEntity as l where l.lastCheck = :parameterToTheFunction")
    fun getByDate(parameterToTheFunction: Date): List<LinkEntity>

    @Query("select min(link.lastCheck) from LinkEntity link")
    fun getMinDate(): Date

    /*@Query("SELECT FIRST FROM LinkEntity WHERE loaded = false")
    fun getNonLoaded(): LinkEntity

    @Query("UPDATE LinkEntity SET loaded = true WHERE url = loadedUrl")
    fun notifyLoaded(loadedUrl: String)

    @Query("UPDATE LinkEntity SET lastCheck = time WHERE url = loadedUrl")
    fun setUpdatedTime(loadedUrl: String, time: Date = Date())

    @Query("SELECT ALL FROM LinkEntity WHERE source = 'Avito'")
    fun all(): List<LinkEntity>*/
}

fun LinksDao.getOldest(): LinkEntity{
    val minDate = getMinDate()
    //val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
    //dateFormat.format(ans.lastCheck).alsoPrintDebug("old")
    return getByDate(minDate).first()
}