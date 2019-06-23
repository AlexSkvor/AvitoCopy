package com.example.devapi.database.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "CARS")
data class CarEntity(
        @Id
        @Column(name = "originalUrl", nullable = false, unique = true, length = 1023)
        val originalUrl: String = "", //URL объявления

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "actualizationTime", nullable = false)
        val actualizationTime: Date = Date(), //дата последней проверки объявления нашей системой

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "creationTime", nullable = false)
        val creationTime: Date = Date(), //время создания (или обновления) оригинального объявления

        @Column(name = "price", nullable = false)
        val price: Int = -1, //Цена

        @Column(name = "year", nullable = false)
        val year: Int = -1, //Год выпуска //TODO don't forget to use -1 as broken value!

        @Column(name = "mileage", nullable = false)
        val mileage: Int = -1, //Пробег

        @Column(name = "source", nullable = false)
        val source: String = "", //Источник (Авито/Автору/Юла)

        @Column(name = "city", nullable = false)
        val city: String = "", //город продажи

        @Column(name = "imageUrl", nullable = false, length = 1023)
        val imageUrl: String = "", //URL картинки

        @Column(name = "tradeMark", nullable = false)
        val tradeMark: String = "", //торговая марка

        @Column(name = "model", nullable = false)
        val model: String = "", //модель

        @Column(name = "color", nullable = false)
        val color: String = "", //цвет

        @Column(name = "driveUnit", nullable = false)
        val driveUnit: String = "", //Привод

        @Column(name = "bodyType", nullable = false)
        val bodyType: String = "", //Тип кузова

        @Column(name = "steeringSide", nullable = false)
        val steeringSide: String = "", //Руль (левый, правый)

        @Column(name = "comment", nullable = false, length = 20000)
        var comment: String = "", //комментарий продовца

        @Column(name = "generation", nullable = false)
        val generation: String = "", //поколение

        @Column(name = "modification", nullable = false)
        val modification: String = "", //модификация

        @Column(name = "condition", nullable = false)
        val condition: String = "", //состояние

        @Column(name = "ptsOwners", nullable = false)
        val ptsOwners: String = "", //количество владельцев

        @Column(name = "vinNumber", nullable = false)
        val vinNumber: String = "", //номер VIN

        @Column(name = "doorsNumber", nullable = false)
        val doorsNumber: String = "", //количество дверей

        @Column(name = "engineType", nullable = false)
        val engineType: String = "", //тип двигателя

        @Column(name = "transmission", nullable = false)
        val transmission: String = "", //подвеска

        @Column(name = "completion", nullable = false)
        val completion: String = "", //комплектация

        @Column(name = "watchPlace", nullable = false)
        val watchPlace: String = "", //место осмотра

        @Column(name = "powerSteering", nullable = false, length = 10000)
        val powerSteering: String = "", //Усилитель руля

        @Column(name = "climateControl", nullable = false, length = 10000)
        val climateControl: String = "", //Управление климатом

        @Column(name = "salon", nullable = false, length = 10000)
        val salon: String = "", //Салон

        @Column(name = "heating", nullable = false, length = 10000)
        val heating: String = "", //Обогрев

        @Column(name = "windowsPower", nullable = false, length = 10000)
        val windowsPower: String = "", //Электростеклоподъемники

        @Column(name = "electricDrive", nullable = false, length = 10000)
        val electricDrive: String = "", //Электропривод

        @Column(name = "settingsMemory", nullable = false, length = 10000)
        val settingsMemory: String = "", //Память настроек

        @Column(name = "drivingAssistance", nullable = false, length = 10000)
        val drivingAssistance: String = "", //Помощь при вождении

        @Column(name = "antitheftSystem", nullable = false, length = 10000)
        val antitheftSystem: String = "", //Противоугонная система

        @Column(name = "airBag", nullable = false, length = 10000)
        val airBag: String = "", //Подушки безопасности

        @Column(name = "activeSafety", nullable = false, length = 10000)
        val activeSafety: String = "", //Активная безопасность

        @Column(name = "multimediaAndNavigation", nullable = false, length = 10000)
        val multimediaAndNavigation: String = "", //Мультимедиа и навигация

        @Column(name = "audioSystem", nullable = false, length = 10000)
        val audioSystem: String = "", //Аудиосистема

        @Column(name = "tiresAndWheels", nullable = false, length = 10000)
        val tiresAndWheels: String = "", //Шины и диски

        @Column(name = "informationAboutTO", nullable = false, length = 10000)
        val informationAboutTO: String = "", //Информация о ТО

        @Column(name = "headlights", nullable = false, length = 10000)
        val headlights: String = "" //Фары
)