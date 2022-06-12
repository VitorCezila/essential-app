package com.cezila.essential.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DrinkEntity(
    @PrimaryKey val id: Int? = null,
    val idDrink: String,
    val name: String,
    val image: String?,
    val description: String,
    val alcoholContent: Int,
    val difficulty: Int,
    val ingredients: String,
    val steps: String,
    val author: String
)