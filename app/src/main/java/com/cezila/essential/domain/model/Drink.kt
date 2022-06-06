package com.cezila.essential.domain.model

data class Drink(
    val id: String,
    val name: String,
    val image: String?,
    val description: String,
    val alcoholContent: Int,
    val difficulty: Int,
    val ingredients: List<Ingredient>,
    val steps: List<Step>,
    val author: String
)
