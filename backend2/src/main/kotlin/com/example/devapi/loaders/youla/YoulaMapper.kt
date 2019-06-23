package com.example.devapi.loaders.youla

import com.example.devapi.database.entities.CarEntity
import com.example.devapi.utils.filterDigits
import com.example.devapi.utils.toIntOr
import com.example.devapi.utils.youla
import java.util.*

object YoulaMapper {

    fun fromMap(map: Map<String, String>,
                original: String,
                city: String,
                photo: String,
                creationTime: Date): CarEntity {
        return CarEntity(
                originalUrl = original,
                actualizationTime = Date(),
                creationTime = creationTime,
                price = map["Цена"]?.filterDigits().toIntOr(-1),
                year = map["Год выпуска"]?.filterDigits().toIntOr(-1),
                mileage = map["Пробег"]?.filterDigits().toIntOr(-1),
                source = youla,
                city = city,
                imageUrl = photo,
                tradeMark = map["Марка"] ?: "",
                model = map["Модель"] ?: "",
                generation = map["Поколение"] ?: "",
                color = map["Цвет"] ?: "",
                driveUnit = map["Привод"] ?: "",
                bodyType = map["Кузов"]?.substringBefore(" ") ?: "",
                steeringSide = map["Руль"] ?: "",
                comment = map["Комментарий"] ?: "",
                ptsOwners = map["Владельцев"] ?: "",
                doorsNumber = map["Кузов"]?.substringAfter(" ") ?: "",
                engineType = map["Двигатель"]?.substringBefore(" ") ?: "",
                salon = map["Экстерьер и салон"] ?: "",
                climateControl = map["Комфорт"] ?: "",
                antitheftSystem = map["Безопасность"] ?: "",
                multimediaAndNavigation = map["Мультимедиа и электроника"] ?: ""
        )
    }
}