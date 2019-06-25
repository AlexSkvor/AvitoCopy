package com.example.devapi.controllers.airplanes

import com.example.devapi.database.entities.PopularCarEntity
import com.example.devapi.utils.firstUpper

data class ModelMark(
        val model: String,
        val mark: String,
        val number: Int
) {
    constructor(entity: PopularCarEntity) : this(
            model = entity.model.firstUpper(),
            mark = entity.mark.firstUpper(),
            number = entity.number
    )
}