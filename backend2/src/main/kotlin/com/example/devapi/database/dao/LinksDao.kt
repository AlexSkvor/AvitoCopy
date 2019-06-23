package com.example.devapi.database.dao

import com.example.devapi.database.entities.LinkEntity
import com.example.devapi.utils.avito
import com.example.devapi.utils.avtoRu
import com.example.devapi.utils.youla
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface LinksDao : CrudRepository<LinkEntity, String> {

    @Query("from LinkEntity as l where l.lastCheck = :parameterToTheFunction and l.source = :site")
    fun getByDate(parameterToTheFunction: Date, site: String): List<LinkEntity>

    @Query("select min(link.lastCheck) from LinkEntity link where link.source = :site")
    fun getMinDate(site: String): Date

    @Query("select top 1 * FROM LINKS where LOADED = 0 and SOURCE = :site", nativeQuery = true)
    fun getNonLoaded(site: String): LinkEntity

    @Query("SELECT COUNT(*) FROM LINKS where LOADED = 0 and SOURCE = :site", nativeQuery = true)
    fun nonLoadedCount(site: String): Int

    @Query("SELECT DISTINCT CITY FROM LINKS", nativeQuery = true)
    fun getAllCities(): List<String>

    @Query("SELECT DISTINCT CITY FROM LINKS WHERE LOADED = 'true'", nativeQuery = true)
    fun getLoadedCities(): List<String>
}

fun LinksDao.getOldest(source: String): LinkEntity {
    val minDate = getMinDate(source)
    return getByDate(minDate, source).first()
}

fun LinksDao.nextLink(source: String): LinkEntity {
    return if (nonLoadedCount(source) > 0) getNonLoaded(source)
    else getOldest(source)
}

fun LinksDao.replace(newEntity: LinkEntity){
    deleteById(newEntity.url)
    save(newEntity)
}

fun LinksDao.nextAvitoLink() = nextLink(avito)
fun LinksDao.nextYoulaLink() = nextLink(youla)
fun LinksDao.nextAvtoRuLink() = nextLink(avtoRu)

val LinksDao.empty: Boolean
    get() = count() < 1