package com.avito_copy.demo.entities.front

object FrontCarMapper {

    fun fromMap(map: Map<String, String>, price: String, imageUrl: String,
                comment: String?, advanced: Map<String, List<String>>, original: String
    ): FrontCar {
        return FrontCar(
                imageUrl = imageUrl,
                originalUrl = original,
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
                        comment = comment ?: "",
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