package com.cezila.essential.domain.model

data class Ingredient (
    val id: String,
    val amount: Double,
    val name: String,
    val originalName: String,
    val unit: String
)