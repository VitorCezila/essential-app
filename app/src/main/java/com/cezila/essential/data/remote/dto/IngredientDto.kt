package com.cezila.essential.data.remote.dto

import com.cezila.essential.domain.model.IngredientType

data class IngredientDto(
    val id: String,
    val amount: String,
    val name: String,
    val originalName: String,
    val unit: String,
    val ingredientType: IngredientType
)