package com.example.devapi.controllers.requests

data class TradeMarksRequest(
        val marksAndModels: List<MarkWithModels>
)

data class MarkWithModels(
        val mark: String,
        val models: List<String>
)