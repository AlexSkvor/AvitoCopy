package com.example.devapi.database.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "PRECOUNT")
class PrecountEntity(
        @Id
        @Column(name = "FULL_NAME", nullable = false, unique = true, length = 128)
        val fullName: String = "",

        @Column(name = "MARK", nullable = false)
        var mark: String = "",

        @Column(name = "MODEL", nullable = false)
        val model: String = "",

        @Column(name = "YEAR", nullable = false)
        val year: Int = 0,

        @Column(name = "NUMBER", nullable = false)
        val number: Int = 0,

        @Column(name = "MEDIAN_PRICE", nullable = false)
        val medianPrice: Int = 0
)