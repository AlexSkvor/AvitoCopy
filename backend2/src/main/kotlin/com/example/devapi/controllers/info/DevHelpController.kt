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

        instructions += "$version/info/data/count -> Количество машин, загруженных в базу данных (аттрибут source - получить данные по одному источнику) <a href = $address/info/data/count>Пример</a> <br>"
        instructions += "$version/info/data/tradeMarks -> Все возможные торговые марки машин <a href = $address/info/data/tradeMarks>Пример</a><br>"
        instructions += "$version/info/data/colors -> Все возможные цвета машин <a href = $address/info/data/colors>Пример</a><br>"
        instructions += "$version/info/data/sorts -> Все возможные сортировки машин <a href = $address/info/data/sorts>Пример</a><br>"
        instructions += "$version/info/data/bodyTypes -> Все возможные типы корпуса машин <a href = $address/info/data/bodyTypes>Пример</a><br>"
        instructions += "$version/info/data/models -> Все возможные модели машин (ОБЯЗАТЕЛЬНЫЙ параметр tradeMark)<a href = $address/info/data/models?tradeMark=audi>Пример</a><br>"
        instructions += "$version/info/data/cities -> Города, по которым есть объявления в базе. \nДобавьте параметр ?all=true, чтобы увидеть ВСЕ города <a href = $address/info/data/cities?all=true>Пример</a><br>"
        instructions += "$version/info/data/sources -> Источники данных <a href = $address/info/data/sources>Пример</a><br>"
        instructions += "$version/help/requests/popular/cars -> Самолетики <a href = $address/help/requests/popular/cars>Пример</a><br>"

        instructions += "$version/search/data -> сложный запрос, <a href = $address/help/requests/search/data>Подробности</a><br>"
        return instructions
    }

    @RequestMapping("/requests/popular/cars")
    @ResponseBody
    fun popularHelp(): String {
        var instructions = ""
        instructions += "$version/help/requests/popular/cars -> Сейчас Вы здесь!<br><br>"

        instructions += "$version/popular/models -> Информация о самых часто продаваемых машинах" +
                " <a href = $address/popular/models?city=moskva>Пример</a> <br>" +
                "$tab Обязательный параметр - city (город) список допустимых значений: <a href = $address/popular/cities>Список</a><br><br>"

        instructions += "$version/popular/cities -> Список городов в системе <a href = $address/popular/cities>Пример</a><br><br>"
        instructions += "$version/popular/not-loaded -> Сколько запросов надо еще сделать до полного распарса данных <a href = $address/popular/not-loaded>Пример</a><br><br>"
        instructions += "$version/popular/reload -> Начать загрузку сначала (уже загруженные <br>" +
                "$tab данные не пропадут, будут позже перезаписаны)<a href = $address/popular/reload>Пример</a><br><br>"

        instructions += "$version/popular/addCity -> Добавить город в очередь для загрузки <a href = $address/popular/addCity?city=moskva>Пример</a><br>"+
        "$tab Обязательный параметр - city (город) из запроса на авито"

        return instructions
    }

    @RequestMapping("/requests/search/cars")
    @ResponseBody
    fun searchCarsDocs(): String {
        var instructions = ""
        instructions += "Инструкции для поискового запроса по машинам: <a href = $address/search/data>Пример</a><br>"
        instructions += "Параметры запроса:<br>"

        instructions += "${bt}skip: <br>$tab Валидация(Целое положительное число)<br>" +
                "$tab Описание(Начинать выдачу мошин с номера skip+1)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(0)<br>" +
                "$tab <a href = $address/search/data?skip=20>Пример</a><br>"

        instructions += "${bt}take: <br>$tab Валидация(Целое положительное число)<br>" +
                "$tab Описание(Выдать в ответе take машин)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(3)<br>" +
                "$tab <a href = $address/search/data?take=20>Пример</a><br>"

        instructions += "${bt}colors: <br>$tab Валидация(один/несколько из <a href = $address/info/data/colors>Список</a> или не задан)<br>" +
                "$tab Описание(Выдавать в ответе машины указаных цветов)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(не задан)<br>" +
                "$tab <a href = $address/search/data?colors=чёрный&colors=розовый>Пример</a><br>"

        instructions += "${bt}bodyTypes: <br>$tab Валидация(один/несколько несколько из <a href = $address/info/data/bodyTypes>Список</a> или не задан)<br>" +
                "$tab Описание(Выдавать в ответе машины, имеющие один из заданных типов кузова)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(не задан)<br>" +
                "$tab <a href = $address/search/data?bodyTypes=купе&bodyTypes=микроавтобус>Пример</a><br>"

        instructions += "${bt}sort: <br>$tab Валидация(один из <a href = $address/info/data/sorts>Список</a> или не задан)<br>" +
                "$tab Описание(Выдавать в ответе машины в указанном порядке)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(Дешевые)<br>" +
                "$tab <a href = $address/search/data?sort=Старые>Пример</a><br>"

        instructions += "${bt}minPrice: <br>$tab Валидация(Целое число)<br>" +
                "$tab Описание(Выдавать в ответе машины ценой большей или равной minPrice)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(0)<br>" +
                "$tab <a href = $address/search/data?minPrice=500000>Пример</a><br>"

        instructions += "${bt}maxPrice: <br>$tab Валидация(Целое число)<br>" +
                "$tab Описание(Выдавать в ответе машины ценой меньшей или равной maxPrice)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(99999999)<br>" +
                "$tab <a href = $address/search/data?maxPrice=500000>Пример</a><br>"

        instructions += "${bt}minYear: <br>$tab Валидация(Целое число)<br>" +
                "$tab Описание(Выдавать в ответе машины, выпущенные не раньше minYear)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(0)<br>" +
                "$tab <a href = $address/search/data?minYear=2018>Пример</a><br>"

        instructions += "${bt}maxYear: <br>$tab Валидация(Целое число)<br>" +
                "$tab Описание(Выдавать в ответе машины, выпущенные не позжу maxYear)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(999999)<br>" +
                "$tab <a href = $address/search/data?maxYear=2000>Пример</a><br>"

        instructions += "${bt}dangerMileage: <br>$tab Валидация(Целое число от 0 до 100)<br>" +
                "$tab На какой процент должно отличаться значение поля Пробег, чтобы был выставлен флаг ОПАСНО, НИЗКИЙ/ВЫСОКИЙ пробег (процент от среднего значения по запросу)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(50)<br>" +
                "$tab <a href = $address/search/data?dangerMileage=0>Пример</a><br>"

        instructions += "${bt}dangerPrice: <br>$tab Валидация(Целое число от 0 до 100)<br>" +
                "$tab На какой процент должно отличаться значение поля Цена, чтобы был выставлен флаг ОПАСНО, НИЗКАЯ/ВЫСОКАЯ ценв (процент от среднего значения по запросу)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(50)<br>" +
                "$tab <a href = $address/search/data?dangerPrice=0>Пример</a><br>"

        instructions += "${bt}cities: <br>$tab Валидация(один/несколько несколько из <a href = $address/info/data/cities>Список</a> или не задан)<br>" +
                "$tab Описание(Выдавать в ответе машины, продающиеся в одном из городов списка)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(не задан)<br>" +
                "$tab <a href = $address/search/data?cities=Калининград>Пример</a><br>"

        instructions += "${bt}sources: <br>$tab Валидация(один/несколько несколько из <a href = $address/info/data/sources>Список</a> или не задан)<br>" +
                "$tab Описание(Выдавать в ответе машины, сведения о которых получены из указанных источников)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(не задан)<br>" +
                "$tab <a href = $address/search/data?sources=Youla>Пример</a><br>"

        instructions += "${bt}filterResellers: <br>$tab Валидация(true/false/не задан)<br>" +
                "$tab Описание(Если задан true, отфильтрует перекупов)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(false)<br>" +
                "$tab <a href = $address/search/data?filterResellers=true&take=1000>Пример</a><br>"

        instructions += "<br><br>${bt}Тело запроса: <br>$tab Валидация(должен иметь вид {\"marksAndModels\" : [{\"mark\":\"audi\", \"models\":[\"a3\",\"a7\"]}, {\"mark\":\"reanault\", \"models\":[\"logan\"]}]} или не задан)<br>" +
                "$tab Описание(Если задано, то выдача будет отфильтрована по указанным маркам и соответствующим им моделям)<br>" +
                "$tab Обязательный(НЕТ)<br>" +
                "$tab По умолчанию(не задано)<br>" +
                "$tab Как задать в HTML запросе не знаю, так что примера НЕТУ<br><br><br>"

        instructions += "<a href = $address/help/>Вернуться к списку API</a>"
        return instructions
    }
}