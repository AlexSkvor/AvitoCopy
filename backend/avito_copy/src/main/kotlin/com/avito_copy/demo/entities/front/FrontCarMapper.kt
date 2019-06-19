package com.avito_copy.demo.entities.front

object FrontCarMapper {

    fun fromMap(map: Map<String, String>, price: String, imageUrl: String): FrontCar {
        return FrontCar(
                imageUrl = imageUrl,
                tradeMark = map["Марка:"] ?: "",
                model = map["Модель:"] ?: "",
                color = map["Цвет:"] ?: "",
                driveUnit = map["Привод:"] ?: "",
                price = price,
                year = map["Год выпуска:"] ?: "",
                bodyType = map["Тип кузова:"] ?: "",
                steeringSide = map["Руль:"] ?: "",
                mileage = (map["Пробег:"] ?: "").filter { it in '0'..'9' } + " км",
                additionalInfo = AdditionalCarInfo(
                        generation = map["Поколение:"] ?: "",
                        modification = map["Модификация:"] ?: "",
                        condition = map["Состояние:"] ?: "",
                        ptsOwners = map["Владельцев по ПТС:"] ?: "",
                        vinNumber = map["VIN или номер кузова:"] ?: "",
                        doorsNumber = map["Количество дверей:"] ?: "",
                        engineType = map["Тип двигателя:"] ?: "",
                        transmission = map["Коробка передач:"] ?: "",
                        completion = map["Комплектация:"] ?: "",
                        watchPlace = map["Место осмотра:"] ?: ""
                )
        )
    }
}