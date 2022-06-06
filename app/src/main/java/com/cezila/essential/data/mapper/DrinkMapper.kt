package com.cezila.essential.data.mapper

import com.cezila.essential.data.remote.dto.DrinkDto
import com.cezila.essential.data.remote.dto.IngredientDto
import com.cezila.essential.data.remote.dto.StepDto
import com.cezila.essential.domain.model.Drink
import com.cezila.essential.domain.model.Ingredient
import com.cezila.essential.domain.model.Step

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
                unit = dto.unit
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