package com.example.devapi.loaders.youla

import com.example.devapi.database.entities.CarEntity
import com.example.devapi.utils.antiSpace
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
                city = city.antiSpace(),
                imageUrl = photo,
                tradeMark = map["Марка"].antiSpace(),
                model = map["Модель"].antiSpace(),
                generation = map["Поколение"].antiSpace(),
                color = map["Цвет"].antiSpace(),
                driveUnit = map["Привод"].antiSpace(),
                bodyType = map["Кузов"]?.substringBefore(" ").antiSpace(),
                steeringSide = map["Руль"].antiSpace(),
                comment = map["Комментарий"].antiSpace(),
                ptsOwners = map["Владельцев"].antiSpace(),
                doorsNumber = map["Кузов"]?.substringAfter(" ").antiSpace(),
                engineType = map["Двигатель"]?.substringBefore(" ").antiSpace(),
                salon = map["Экстерьер и салон"].antiSpace(),
                climateControl = map["Комфорт"].antiSpace(),
                antitheftSystem = map["Безопасность"].antiSpace(),
                multimediaAndNavigation = map["Мультимедиа и электроника"].antiSpace()
        )
    }
}