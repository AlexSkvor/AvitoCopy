package com.example.devapi.database.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "LINKS")
data class LinkEntity(
        @Id
        @Column(name = "url", nullable = false, unique = true, length = 511)
        val url: String = "",

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "lastCheck", nullable = false)
        val lastCheck: Date = Date(),

        @Column(name = "loaded", nullable = false)
        var loaded: Boolean = false,

        @Column(name = "source", nullable = false)
        val source: String = "",

        @Column(name = "city", nullable = false)
        val city: String = ""
)