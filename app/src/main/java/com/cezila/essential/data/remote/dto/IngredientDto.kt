package com.cezila.essential.data.remote.dto

data class IngredientDto(
    val id: String,
    val amount: Double,
    val name: String,
    val originalName: String,
    val unit: String
)