package com.avito_copy.demo.entities.front

import com.avito_copy.demo.extensions.antiSpace

object FrontCarMapper {

    fun fromMap(map: Map<String, String>, price: String, imageUrl: String,
                comment: String?, advanced: Map<String, List<String>>, original: String
    ): FrontCar {
        return FrontCar(
                imageUrl = imageUrl,
                originalUrl = original,
                tradeMark = map["Марка:"].antiSpace(),
                model = map["Модель:"].antiSpace(),
                color = map["Цвет:"].antiSpace(),
                driveUnit = map["Привод:"].antiSpace(),
                price = price.filter { it in '0'..'9' },
                year = map["Год выпуска:"]?.filter { it in '0'..'9' } ?: "",
                bodyType = map["Тип кузова:"].antiSpace(),
                steeringSide = map["Руль:"].antiSpace(),
                mileage = (map["Пробег:"])?.filter { it in '0'..'9' } + " км",
                additionalInfo = AdditionalCarInfo(
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
                        watchPlace = map["Место осмотра:"].antiSpace()
                ),
                advancedInfo = AdvancedInfo(
                        powerSteering = advanced["Усилитель руля"] ?: listOf(),
                        climateControl = advanced["Управление климатом"] ?: listOf(),
                        salon = advanced["Салон"] ?: listOf(),
                        heating = advanced["Обогрев"] ?: listOf(),
                        windowsPower = advanced["Электростеклоподъемники"] ?: listOf(),
                        electricDrive = advanced["Электропривод"] ?: listOf(),
                        settingsMemory = advanced["Память настроек"] ?: listOf(),
                        drivingAssistance = advanced["Помощь при вождении"] ?: listOf(),
                        antitheftSystem = advanced["Противоугонная система"] ?: listOf(),
                        airBag = advanced["Подушки безопасности"] ?: listOf(),
                        activeSafety = advanced["Активная безопасность"] ?: listOf(),
                        multimediaAndNavigation = advanced["Мультимедиа и навигация"] ?: listOf(),
                        audioSystem = advanced["Аудиосистема"] ?: listOf(),
                        tiresAndWheels = advanced["Шины и диски"] ?: listOf(),
                        headlights = advanced["Фары"] ?: listOf(),
                        informationAboutTO = advanced["Данные о ТО"] ?: listOf()
                )
        )
    }
}