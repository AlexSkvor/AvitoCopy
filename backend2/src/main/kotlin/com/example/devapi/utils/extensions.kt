package com.example.devapi.utils

import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat

fun <T> List<T>.withSkipTake(skip: Int, take: Int): List<T> {
    return when {
        skip < 0 || take < 0 -> listOf()
        this.size > skip + take -> this.subList(skip, skip + take)
        this.size > skip -> this.subList(skip, this.size)
        else -> listOf()
    }
}

fun String.filterDigits(): String =
        this.filter { it in '0'..'9' }


fun wrongArguments(contained: List<*>, seePermitted: String): String {
    return "your request contained wrong arguments $contained\n you can see permitted with $seePermitted"
}

val antiSpacePattern = "\\s+".toRegex()
fun String?.antiSpace() =
        this?.trim()?.replace(antiSpacePattern, " ") ?: ""

fun String.lessEqualsThen(num: Int): Boolean {
    return try {
        if (this.isEmpty()) false
        else this.filterDigits().toInt() <= num
    } catch (e: Exception) {
        alsoPrintDebug("lessEqualsThen $e")
        false
    }
}

fun String.biggerEqualsThen(num: Int): Boolean {
    return try {
        if (this.isEmpty()) false
        else this.filterDigits().toInt() >= num
    } catch (e: Exception) {
        alsoPrintDebug("biggerEqualsThen $e")
        false
    }
}

fun String.toIntOrBig(): Int = try {
    this.toInt()
} catch (e: Exception) {
    99999999
}

fun String.toIntOrZero(): Int = try {
    this.toInt()
} catch (e: Exception) {
    0
}

fun String?.toIntOr(default: Int): Int = try {
    this?.toInt() ?: default
} catch (e: Exception) {
    default
}

fun <T> List<List<T>>.merge(): List<T> {
    val result = mutableListOf<T>()
    this.forEach { result += it }
    return result.distinct()
}

fun <T> T.alsoPrintDebug(msg: String = ""): T {
    val file = File(fileLog)
    if (!file.exists()) file.writeText("")
    file.appendText(msg + "\n")
    println("$msg...........$this")
    return this
}

fun loggedTry(block: () -> Unit) {
    try {
        block()
    } catch (e: Exception) {
        e.stackTrace.alsoPrintDebug("Error")
    }
}

fun List<String>.stringFormat(): String =
        if (this.size == 1) this.first()
        else {
            var value = ""
            this.forEach { value += "$it; " }
            value
        }

val dateFormat: SimpleDateFormat
    get() = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")