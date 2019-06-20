package com.avito_copy.demo.controllers

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

    private val tab = "&nbsp&nbsp&nbsp&nbsp"
    private val bt = "$tab$tab$tab"

    @RequestMapping("/")
    @ResponseBody
    fun allRequestsDocs(): String {
        var instructions = ""
        instructions += "Методы API:<br>"
        instructions += "devApi/help/ -> Сейчас Вы здесь!<br>"
        instructions += "devApi/info/cars/count -> Количество машин, загруженных в базу данных <a href = http://84.201.139.189:8080/devApi/info/cars/count>Пример</a> <br>"
        instructions += "devApi/info/cars/tradeMarks -> Все возможные торговые марки машин <a href = http://84.201.139.189:8080/devApi/info/cars/tradeMarks>Пример</a><br>"
        instructions += "devApi/info/cars/colors -> Все возможные цвета машин <a href = http://84.201.139.189:8080/devApi/info/cars/colors>Пример</a><br>"
        instructions += "devApi/search/cars -> сложный запрос, <a href = http://84.201.139.189:8080/devApi/help/requests/search/cars>Подробности</a><br>"
        return instructions
    }

    @RequestMapping("/requests/search/cars")
    @ResponseBody
    fun searchCarsDocs(): String {
        var instructions = ""
        instructions += "Инструкции для поискового запроса по машинам: <a href = http://84.201.139.189:8080/devApi/search/cars>Пример</a><br>"
        instructions += "Параметры запроса:<br>"
        instructions += "${bt}skip: <br>${bt}${bt}${bt}Валидация(Целое положительное число)<br>" +
                "${bt}${bt}${bt}Описание(Начинать выдачу мошин с номера skip+1)<br>" +
                "${bt}${bt}${bt}Обязательный(НЕТ)<br>" +
                "${bt}${bt}${bt}По умолчанию(0)<br>" +
                "${bt}${bt}${bt}<a href = http://84.201.139.189:8080/devApi/search/cars?skip=20>Пример</a><br>"
        instructions += "${bt}take: <br>${bt}${bt}${bt}Валидация(Целое положительное число)<br>" +
                "${bt}${bt}${bt}Описание(Выдать в ответе take машин)<br>" +
                "${bt}${bt}${bt}Обязательный(НЕТ)<br>" +
                "${bt}${bt}${bt}По умолчанию(3)<br>" +
                "${bt}${bt}${bt}<a href = http://84.201.139.189:8080/devApi/search/cars?take=20>Пример</a><br>"
        instructions += "${bt}tradeMarks: <br>${bt}${bt}${bt}Валидация(один из devApi/info/cars/tradeMarks или не задан, возможно задание нескольких значений одновременно)<br>" +
                "${bt}${bt}${bt}Описание(Выдавать в ответе машины указаных марок)<br>" +
                "${bt}${bt}${bt}Обязательный(НЕТ)<br>" +
                "${bt}${bt}${bt}По умолчанию(не задан)<br>" +
                "${bt}${bt}${bt}<a href = http://84.201.139.189:8080/devApi/search/cars?tradeMarks=Audi&tradeMarks=BMW>Пример</a><br>"
        instructions += "${bt}colors: <br>${bt}${bt}${bt}Валидация(один из devApi/info/cars/colors или не задан, возможно задание нескольких значений одновременно)<br>" +
                "${bt}${bt}${bt}Описание(Выдавать в ответе машины указаных цветов)<br>" +
                "${bt}${bt}${bt}Обязательный(НЕТ)<br>" +
                "${bt}${bt}${bt}По умолчанию(не задан)<br>" +
                "${bt}${bt}${bt}<a href = http://84.201.139.189:8080/devApi/search/cars?colors=чёрный&colors=розовый>Пример</a><br>"
        instructions += "${bt}minPrice: <br>${bt}${bt}${bt}Валидация(Целое число)<br>" +
                "${bt}${bt}${bt}Описание(Выдавать в ответе машины ценой большей или равной minPrice)<br>" +
                "${bt}${bt}${bt}Обязательный(НЕТ)<br>" +
                "${bt}${bt}${bt}По умолчанию(0)<br>" +
                "${bt}${bt}${bt}<a href = http://84.201.139.189:8080/devApi/search/cars?minPrice=500000>Пример</a><br>"
        instructions += "${bt}maxPrice: <br>${bt}${bt}${bt}Валидация(Целое число)<br>" +
                "${bt}${bt}${bt}Описание(Выдавать в ответе машины ценой меньшей или равной maxPrice)<br>" +
                "${bt}${bt}${bt}Обязательный(НЕТ)<br>" +
                "${bt}${bt}${bt}По умолчанию(999999)<br>" +
                "${bt}${bt}${bt}<a href = http://84.201.139.189:8080/devApi/search/cars?maxPrice=500000>Пример</a><br>"
        instructions += "${bt}minYear: <br>${bt}${bt}${bt}Валидация(Целое число)<br>" +
                "${bt}${bt}${bt}Описание(Выдавать в ответе машины, выпущенные не раньше minYear)<br>" +
                "${bt}${bt}${bt}Обязательный(НЕТ)<br>" +
                "${bt}${bt}${bt}По умолчанию(0)<br>" +
                "${bt}${bt}${bt}<a href = http://84.201.139.189:8080/devApi/search/cars?minYear=2018>Пример</a><br>"
        instructions += "${bt}maxYear: <br>${bt}${bt}${bt}Валидация(Целое число)<br>" +
                "${bt}${bt}${bt}Описание(Выдавать в ответе машины, выпущенные не позжу maxYear)<br>" +
                "${bt}${bt}${bt}Обязательный(НЕТ)<br>" +
                "${bt}${bt}${bt}По умолчанию(999999)<br>" +
                "${bt}${bt}${bt}<a href = http://84.201.139.189:8080/devApi/search/cars?maxYear=2000>Пример</a><br>"


        instructions += "<a href = http://84.201.139.189:8080/devApi/help/>Вернуться к списку API</a>"
        return instructions
    }
}