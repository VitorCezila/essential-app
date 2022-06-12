package com.cezila.essential.data.mapper

import com.cezila.essential.data.local.DrinkEntity
import com.cezila.essential.data.remote.dto.DrinkDto
import com.cezila.essential.data.remote.dto.IngredientDto
import com.cezila.essential.data.remote.dto.StepDto
import com.cezila.essential.domain.model.Drink
import com.cezila.essential.domain.model.Ingredient
import com.cezila.essential.domain.model.Step
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Drink.toDrinkEntity(): DrinkEntity {
    return DrinkEntity(
        idDrink = id,
        name = name,
        image = image,
        description = description,
        alcoholContent = alcoholContent,
        difficulty = difficulty,
        ingredients = ingredients.toListIngredientEntity(),
        steps = steps.toListStepEntity(),
        author = author
    )
}

fun DrinkEntity.toDrink(): Drink {
    return Drink(
        id = idDrink,
        name = name,
        image = image,
        description = description,
        alcoholContent = alcoholContent,
        difficulty = difficulty,
        ingredients = ingredients.toListIngredient(),
        steps = steps.toListStep(),
        author = author
    )
}

fun DrinkDto.toDrink(): Drink {
    return Drink(
        id = id,
        name = name,
        image = image,
        description = description,
        alcoholContent = alcoholContent,
        difficulty = difficulty,
        ingredients = ingredients.toListIngredient(),
        steps = steps.toListStep(),
        author = author
    )
}

private fun List<IngredientDto>.toListIngredient(): List<Ingredient> {
    val listIngredients = arrayListOf<Ingredient>()
    this.map { dto ->
        listIngredients.add(
            Ingredient(
                id = dto.id,
                name = dto.name,
                amount = dto.amount,
                originalName = dto.originalName,
                unit = dto.unit,
                ingredientType = dto.ingredientType
            )
        )
    }
    return listIngredients
}

private fun List<StepDto>.toListStep(): List<Step> {
    val listStep = arrayListOf<Step>()
    this.map { dto ->
        listStep.add(
            Step(
                step = dto.step,
                number = dto.number
            )
        )
    }
    return listStep
}

private fun List<Ingredient>.toListIngredientEntity(): String {
    return Gson().toJson(this)
}

private fun List<Step>.toListStepEntity(): String {
    return Gson().toJson(this)
}

private fun String.toListIngredient(): List<Ingredient> {
    val typeToken = object : TypeToken<List<Ingredient>>() {}.type
    return Gson().fromJson(this, typeToken)
}

private fun String.toListStep(): List<Step> {
    val typeToken = object : TypeToken<List<Step>>() {}.type
    return Gson().fromJson(this, typeToken)
}