package com.example.devapi.entities.back

/**
 * Created on 6/20/2019
 * @author AlexSkvor
 * */

data class LinkEntity(
        val url: String,
        var loaded: Boolean = false,
        var errored: Boolean = false
)

data class LinksList(
        var list: List<LinkEntity>
)