package com.example.devapi.utils

import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

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
        this?.trim()?.replace(antiSpacePattern, " ")?.toLowerCase() ?: ""

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

fun String.containsOneOf(list: List<String>): Boolean {
    list.forEach {
        if (this.contains(it)) return true
    }
    return false
}

val dateFormat: SimpleDateFormat
    get() = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")

fun Date.frontFormat(): String =
        SimpleDateFormat("MM-dd-HH-mm").format(this)

fun String.firstUpper(): String =
        if (this.isEmpty()) this
        else {
            val first = this.first()
            val end = this.substring(1, this.length)
            first.toUpperCase() + end
        }

