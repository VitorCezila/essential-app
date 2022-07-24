package com.cezila.essential.data.remote.dto

data class DrinkDto(
    val id: String,
    val name: String,
    val image: String?,
    val description: String,
    val alcoholContent: Int,
    val difficulty: Int,
    val ingredients: List<IngredientDto>,
    val steps: List<StepDto>,
    val author: String
)