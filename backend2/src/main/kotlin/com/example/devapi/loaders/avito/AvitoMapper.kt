package com.example.devapi.loaders.avito

import com.example.devapi.database.entities.CarEntity
import com.example.devapi.utils.antiSpace
import com.example.devapi.utils.avito
import com.example.devapi.utils.toIntOr
import java.util.*

object AvitoMapper {

    fun fromMap(map: Map<String, String>,
                price: String,
                imageUrl: String,
                comment: String?,
                original: String,
                city: String,
                creationTime: Date
    ): CarEntity {
        return CarEntity(
                imageUrl = imageUrl,
                originalUrl = original,
                tradeMark = map["Марка:"].antiSpace(),
                model = map["Модель:"].antiSpace(),
                color = map["Цвет:"].antiSpace(),
                driveUnit = map["Привод:"].antiSpace(),
                city = city,
                actualizationTime = Date(),
                creationTime = creationTime,
                source = avito,
                price = price.filter { it in '0'..'9' }.toIntOr(-1),
                year = map["Год выпуска:"]?.filter { it in '0'..'9' }.toIntOr(-1),
                bodyType = map["Тип кузова:"].antiSpace(),
                steeringSide = map["Руль:"].antiSpace(),
                mileage = (map["Пробег:"])?.filter { it in '0'..'9' }.toIntOr(-1),
                comment = comment.antiSpace(),
                generation = map["Поколение:"].antiSpace(),
                modification = map["Модификация:"].antiSpace(),
                condition = map["Состояние:"].antiSpace(),
                ptsOwners = map["Владельцев по ПТС:"].antiSpace(),
                vinNumber = map["VIN или номер кузова:"].antiSpace(),
                doorsNumber = map["Количество дверей:"].antiSpace(),
                engineType = map["Тип двигателя:"].antiSpace(),
                transmission = map["Коробка передач:"].antiSpace(),
                completion = map["Комплектация:"].antiSpace(),
                watchPlace = map["Место осмотра:"].antiSpace(),
                powerSteering = map["Усилитель руля"] ?: "",
                climateControl = map["Управление климатом"] ?: "",
                salon = map["Салон"] ?: "",
                heating = map["Обогрев"] ?: "",
                windowsPower = map["Электростеклоподъемники"] ?: "",
                electricDrive = map["Электропривод"] ?: "",
                settingsMemory = map["Память настроек"] ?: "",
                drivingAssistance = map["Помощь при вождении"] ?: "",
                antitheftSystem = map["Противоугонная система"] ?: "",
                airBag = map["Подушки безопасности"] ?: "",
                activeSafety = map["Активная безопасность"] ?: "",
                multimediaAndNavigation = map["Мультимедиа и навигация"] ?: "",
                audioSystem = map["Аудиосистема"] ?: "",
                tiresAndWheels = map["Шины и диски"] ?: "",
                headlights = map["Фары"] ?: "",
                informationAboutTO = map["Данные о ТО"] ?: ""

        )
    }
}