package com.example.devapi.database.dao

import com.example.devapi.database.entities.LinkEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface LinksDao : CrudRepository<LinkEntity, String> {
    //MERGE INTO TABLE STACKOVERFLOW('abc'); - insert with replace on conflict

    @Query("from LinkEntity as l where l.lastCheck = :parameterToTheFunction and l.source = :site")
    fun getByDate(parameterToTheFunction: Date, site: String): List<LinkEntity>

    @Query("select min(link.lastCheck) from LinkEntity link where link.source = :site")
    fun getMinDate(site: String): Date

    @Query("select top 1 * FROM LINKS where LOADED = 0 and SOURCE = :site", nativeQuery = true)
    fun getNonLoaded(site: String): LinkEntity

    @Query("SELECT COUNT(*) FROM LINKS where LOADED = 0 and SOURCE = :site", nativeQuery = true)
    fun nonLoadedCount(site: String): Int
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

fun LinksDao.nextAvitoLink() = nextLink("Avito")

val LinksDao.empty: Boolean
    get() = count() < 1