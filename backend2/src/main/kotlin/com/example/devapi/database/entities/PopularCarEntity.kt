package com.example.devapi.database.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "CITY_INFO")
data class PopularCarEntity(
        @Id
        @Column(name = "FULL_NAME", nullable = false, unique = true, length = 128)
        val fullName: String = "",

        @Column(name = "CITY", nullable = false)
        val city: String = "",

        @Column(name = "MARK", nullable = false)
        var mark: String = "",

        @Column(name = "MODEL", nullable = false)
        val model: String = "",

        @Column(name = "NUMBER", nullable = false)
        val number: Int = 0,

        @Column(name = "LOADED", nullable = false)
        val loaded: Boolean = false
//TODO replace " " with "_"
)