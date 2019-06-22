package com.example.devapi.loaders.avito

import com.example.devapi.extensions.antiSpace
import com.example.devapi.extensions.toIntOrZero
import java.util.*

object AvitoPlacementDateParser {

    fun dateFromRussianString(str: String): Date {
        val formatted = str
                .replace("размещено", "")
                .trim()
                .antiSpace()

        return when {
            formatted.contains("сегодня") -> getToday(formatted)
            formatted.contains("вчера") -> getYesterday(formatted)
            else -> getByMonth(formatted)
        }
    }

    private fun getToday(str: String): Date =
            Calendar.getInstance().setup()
                    .setHours(hour = hour(str), minute = minute(str)).time

    private fun getYesterday(str: String): Date =
            Calendar.getInstance().setup()
                    .setHours(hour = hour(str), minute = minute(str))
                    .yesterday.time

    private fun getByMonth(str: String): Date = when {
        str.contains("января") -> date(Calendar.JANUARY + 1, str)
        str.contains("февраля") -> date(Calendar.FEBRUARY + 1, str)
        str.contains("марта") -> date(Calendar.MARCH + 1, str)
        str.contains("апреля") -> date(Calendar.APRIL + 1, str)
        str.contains("мая") -> date(Calendar.MAY + 1, str)
        str.contains("июня") -> date(Calendar.JUNE + 1, str)
        str.contains("июля") -> date(Calendar.JULY + 1, str)
        str.contains("августа") -> date(Calendar.AUGUST + 1, str)
        str.contains("сентября") -> date(Calendar.SEPTEMBER + 1, str)
        str.contains("октября") -> date(Calendar.OCTOBER + 1, str)
        str.contains("ноября") -> date(Calendar.NOVEMBER + 1, str)
        str.contains("декабря") -> date(Calendar.DECEMBER + 1, str)
        else -> Date()
    }

    private fun day(str: String): Int = str.substring(0, 2)
            .filter { it in '0'..'9' }.trim().toIntOrZero()

    private fun hour(str: String): Int = str.substring(str.length - 5, str
            .lastIndexOf(':')).filter { it in '0'..'9' }.trim().toIntOrZero()

    private fun minute(str: String): Int = str.substring(str.lastIndexOf(':'), str.length)
            .filter { it in '0'..'9' }.trim().toIntOrZero()

    private fun date(month: Int, str: String): Date =
            Calendar.getInstance().setup()
                    .setDate(month, day(str))
                    .setHours(hour = hour(str), minute = minute(str))
                    .time

    private fun Calendar.setHours(hour: Int, minute: Int): Calendar {
        this.set(Calendar.HOUR_OF_DAY, hour)
        this.set(Calendar.MINUTE, minute)
        return this
    }

    private fun Calendar.setDate(month: Int, day: Int): Calendar {
        this.set(Calendar.MONTH, month)
        this.set(Calendar.DAY_OF_MONTH, day)
        return this
    }

    private fun Calendar.setup(): Calendar {
        this.time = Date()
        this.set(Calendar.MILLISECOND, 0)
        this.set(Calendar.SECOND, 0)
        return this
    }

    private val Calendar.yesterday: Calendar
        get() {
            this.add(Calendar.DAY_OF_MONTH, -1)
            return this
        }
}