package com.example.devapi.entities.front

data class AdditionalCarInfo(
        var comment: String = "",
        val generation: String = "",
        val modification: String = "",
        val condition: String = "",
        val ptsOwners: String = "",
        val vinNumber: String = "",
        val doorsNumber: String = "",
        val engineType: String = "",
        val transmission: String = "",
        val completion: String = "",
        val watchPlace: String = ""
)