package com.example.devapi.controllers.airplanes

import com.example.devapi.controllers.responses.AirPlanesResponse
import com.example.devapi.database.dao.PopularCarsDao
import com.example.devapi.database.entities.PopularCarEntity
import com.example.devapi.utils.ArgumentException
import com.example.devapi.utils.filesDirectory
import org.jsoup.Jsoup
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.util.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/popular")
class PopularCarController(
        private val popularCarsRepository: PopularCarsDao
) {

    @ExceptionHandler
    fun handleIllegalArgumentException(e: IllegalArgumentException, response: HttpServletResponse) {
        response.sendError(HttpStatus.BAD_REQUEST.value())
    }

    @GetMapping("/models")
    fun getPopularCars(@RequestParam(value = "city", required = true) city: String
    ): AirPlanesResponse<ModelMark> {
        validateCity(city)

        val ans = popularCarsRepository.getByCityOrderByNumberDesc(city)
        val data = if (ans.size > 10) ans.subList(0, 10) else ans
        val frontFormat = data.filter { it.number > 0 }.map { ModelMark(it) }
        return AirPlanesResponse(data = frontFormat)
    }

    private fun validateCity(city: String) {
        if (!popularCarsRepository.getAllCities().contains(city))
            throw ArgumentException("Был введен неверный город $city")
    }

    @GetMapping("/cities")
    fun getPopularCars(): AirPlanesResponse<String> {
        val ans = popularCarsRepository.getAllCities()
        return AirPlanesResponse(data = ans)
    }

    @GetMapping("/not-loaded")
    fun detNotLoadedNumber(): AirPlanesResponse<Int> {
        val count = popularCarsRepository.countNotLoaded()
        return AirPlanesResponse(data = listOf(count), message = "Осталось распарсить $count ссылок")
    }

    @GetMapping("/reload")
    fun detNotLoadedNumber(@RequestParam(value = "password", required = false, defaultValue = "") password: String): AirPlanesResponse<Boolean> {
        val condition = (password == "ЖеняЖмот")
        if (condition) popularCarsRepository.startReload()
        return if (condition) AirPlanesResponse(data = listOf(true), message = "Начинаем перевыгрузку данных, они будут постепенно обновляться")
        else throw ArgumentException("Ой, не тот пароль!")
    }

    @GetMapping("/addCity")
    fun addCityWeb(@RequestParam(value = "city", required = true) city: String): AirPlanesResponse<Boolean> {
        if (popularCarsRepository.getAllCities().contains(city))
            throw ArgumentException("Город уже добавлен в систему!")

        val url = "https://www.avito.ru/$city/avtomobili?radius=0"

        try {
            Jsoup.connect(url).get()
        } catch (e: Throwable) {
            throw ArgumentException("Некорректный город, <a href = $url>проверьте сами</a>")
        }

        addCity(city)

        return AirPlanesResponse(data = listOf(true), message = "Город успешно добавлен, скоро начнется выгрузка данных:)")
    }

    private fun addCity(city: String) {
        val marks = File(filesDirectory + "allMarksAddress.txt").readLines()
        marks.forEach {
            popularCarsRepository.save(PopularCarEntity(
                    fullName = UUID.randomUUID().toString(),
                    city = city,
                    mark = it
            ))
        }
    }
}