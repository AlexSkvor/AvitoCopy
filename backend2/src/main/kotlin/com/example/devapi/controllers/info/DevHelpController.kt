package com.example.devapi.controllers.info

import com.example.devapi.utils.address
import com.example.devapi.utils.version
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


/**
 * Created on 6/17/2019
 * @author AlexSkvor
 * */

@RestController
@RequestMapping("/help")
class DevHelpController {

    private val t = "&nbsp&nbsp&nbsp&nbsp"
    private val bt = "$t$t$t"
    private val tab = "$bt$bt$bt"

    @RequestMapping("/")
    @ResponseBody
    fun allRequestsDocs(): String {
        var instructions = ""
        instructions += "Методы API:<br>"
        instructions += "$version/help/ -> Сейчас Вы здесь!<br>"

        instructions += "$version/info/cars/count -> Количество машин, загруженных в базу данных (аттрибут source - получить данные по одному источнику) <a href = $address/info/cars/count>Пример</a> <br>"
        instructions += "$version/info/cars/tradeMarks -> Все возможные торговые марки машин <a href = $address/info/cars/tradeMarks>Пример</a><br>"
        instructions += "$version/info/cars/colors -> Все возможные цвета машин <a href = $address/info/cars/colors>Пример</a><br>"
        instructions += "$version/info/cars/sorts -> Все возможные сортировки машин <a href = $address/info/cars/sorts>Пример</a><br>"
        instructions += "$version/info/cars/bodyTypes -> Все возможные типы корпуса машин <a href = $address/info/cars/bodyTypes>Пример</a><br>"
        instructions += "$version/info/cars/models -> Все возможные модели машин (ОБЯЗАТЕЛЬНЫЙ параметр tradeMark)<a href = $address/info/cars/models?tradeMark=audi>Пример</a><br>"
        instructions += "$version/info/cars/cities -> Города, по которым есть объявления в базе. \nДобавьте параметр ?all=true, чтобы увидеть ВСЕ города <a href = $address/info/cars/cities?all=true>Пример</a><br>"
        instructions += "$version/info/cars/sources -> Источники данных <a href = $address/info/cars/sources>Пример</a><br>"

        instructions += "$version/search/cars -> сложный запрос, <a href = $address/help/requests/search/cars>Подробности</a><br>"
        return instructions
    }

    @RequestMapping("/requests/search/cars")
    @ResponseBody
    fun searchCarsDocs(): String {
        var instructions = ""
        instructions += "Инструкции для поискового запроса по машинам: <a href = $address/search/cars>Пример</a><br>"
        instructions += "Параметры запроса:<br>"

        instructions += "${bt}skip: <br>$tab Валидация(Целое положительное число)<br>" +
                "$tab Описание(Начинать выдачу мошин с номера skip+1)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(0)<br>" +
                "$tab <a href = $address/search/cars?skip=20>Пример</a><br>"

        instructions += "${bt}take: <br>$tab Валидация(Целое положительное число)<br>" +
                "$tab Описание(Выдать в ответе take машин)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(3)<br>" +
                "$tab <a href = $address/search/cars?take=20>Пример</a><br>"

        instructions += "${bt}colors: <br>$tab Валидация(один/несколько из <a href = $address/info/cars/colors>Список</a> или не задан)<br>" +
                "$tab Описание(Выдавать в ответе машины указаных цветов)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(не задан)<br>" +
                "$tab <a href = $address/search/cars?colors=чёрный&colors=розовый>Пример</a><br>"

        instructions += "${bt}bodyTypes: <br>$tab Валидация(один/несколько несколько из <a href = $address/info/cars/bodyTypes>Список</a> или не задан)<br>" +
                "$tab Описание(Выдавать в ответе машины, имеющие один из заданных типов кузова)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(не задан)<br>" +
                "$tab <a href = $address/search/cars?bodyTypes=купе&bodyTypes=микроавтобус>Пример</a><br>"

        instructions += "${bt}sort: <br>$tab Валидация(один из <a href = $address/info/cars/sorts>Список</a> или не задан)<br>" +
                "$tab Описание(Выдавать в ответе машины в указанном порядке)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(Дешевые)<br>" +
                "$tab <a href = $address/search/cars?sort=Старые>Пример</a><br>"

        instructions += "${bt}minPrice: <br>$tab Валидация(Целое число)<br>" +
                "$tab Описание(Выдавать в ответе машины ценой большей или равной minPrice)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(0)<br>" +
                "$tab <a href = $address/search/cars?minPrice=500000>Пример</a><br>"

        instructions += "${bt}maxPrice: <br>$tab Валидация(Целое число)<br>" +
                "$tab Описание(Выдавать в ответе машины ценой меньшей или равной maxPrice)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(99999999)<br>" +
                "$tab <a href = $address/search/cars?maxPrice=500000>Пример</a><br>"

        instructions += "${bt}minYear: <br>$tab Валидация(Целое число)<br>" +
                "$tab Описание(Выдавать в ответе машины, выпущенные не раньше minYear)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(0)<br>" +
                "$tab <a href = $address/search/cars?minYear=2018>Пример</a><br>"

        instructions += "${bt}maxYear: <br>$tab Валидация(Целое число)<br>" +
                "$tab Описание(Выдавать в ответе машины, выпущенные не позжу maxYear)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(999999)<br>" +
                "$tab <a href = $address/search/cars?maxYear=2000>Пример</a><br>"

        instructions += "${bt}dangerMileage: <br>$tab Валидация(Целое число от 0 до 100)<br>" +
                "$tab На какой процент должно отличаться значение поля Пробег, чтобы был выставлен флаг ОПАСНО, НИЗКИЙ/ВЫСОКИЙ пробег (процент от среднего значения по запросу)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(50)<br>" +
                "$tab <a href = $address/search/cars?dangerMileage=0>Пример</a><br>"

        instructions += "${bt}dangerPrice: <br>$tab Валидация(Целое число от 0 до 100)<br>" +
                "$tab На какой процент должно отличаться значение поля Цена, чтобы был выставлен флаг ОПАСНО, НИЗКАЯ/ВЫСОКАЯ ценв (процент от среднего значения по запросу)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(50)<br>" +
                "$tab <a href = $address/search/cars?dangerPrice=0>Пример</a><br>"

        instructions += "${bt}cities: <br>$tab Валидация(один/несколько несколько из <a href = $address/info/cars/cities>Список</a> или не задан)<br>" +
                "$tab Описание(Выдавать в ответе машины, продающиеся в одном из городов списка)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(не задан)<br>" +
                "$tab <a href = $address/search/cars?cities=kaliningrad>Пример</a><br>"

        instructions += "${bt}sources: <br>$tab Валидация(один/несколько несколько из <a href = $address/info/cars/sources>Список</a> или не задан)<br>" +
                "$tab Описание(Выдавать в ответе машины, сведения о которых получены из указанных источников)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(не задан)<br>" +
                "$tab <a href = $address/search/cars?sources=Youla>Пример</a><br>"

        instructions += "<a href = $address/help/>Вернуться к списку API</a>"
        return instructions
    }
}