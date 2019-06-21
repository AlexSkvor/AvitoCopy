package com.example.devapi.entities.back

class MarksModelsList {
    val list: List<MarkModels> = listOf()
}

data class MarkModels(
        val tradeMark: String = "",
        val models: List<String> = listOf()
)