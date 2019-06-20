package com.avito_copy.demo.extensions

import java.lang.Exception

fun <T> List<T>.withSkipTake(skip: Int, take: Int): List<T> {
    return when {
        skip < 0 || take < 0 -> listOf()
        this.size > skip + take -> this.subList(skip, skip + take)
        this.size > skip -> this.subList(skip, this.size)
        else -> listOf()
    }
}

fun wrongArguments(contained: List<*>, seePermitted: String): String {
    return "your request contained wrong arguments $contained\n you can see permitted with $seePermitted"
}

val antiSpacePattern = "\\s+".toRegex()
fun String?.antiSpace() =
        this?.trim()?.replace(antiSpacePattern, " ") ?: ""

fun String.lessEqualsThen(num: Int): Boolean {
    return try {
        this.toInt() <= num
    } catch (e: Exception) {
        println(e)
        false
    }
}

fun String.biggerEqualsThen(num: Int): Boolean {
    return try {
        this.toInt() >= num
    } catch (e: Exception) {
        println(e)
        false
    }
}
