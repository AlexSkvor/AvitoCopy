package com.example.devapi.extensions

import java.lang.Exception

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
        println("lessEqualsThen $e")
        false
    }
}

fun String.biggerEqualsThen(num: Int): Boolean {
    return try {
        if (this.isEmpty()) false
        else this.filterDigits().toInt() >= num
    } catch (e: Exception) {
        println("biggerEqualsThen $e")
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

fun <T> List<List<T>>.merge(): List<T> {
    val result = mutableListOf<T>()
    this.forEach { result += it }
    return result.distinct()
}

fun <T> T.alsoPrintDebug(msg: String = ""): T {
    println("$msg...........$this")
    return this
}