package com.cezila.essential.presentation.drink_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cezila.essential.domain.model.Ingredient
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun IngredientItem(
    modifier: Modifier = Modifier,
    ingredient: Ingredient
) {
    FlowRow(modifier = modifier) {
        Text(
            text = "${ingredient.name}:",
            color = Color.White,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = ingredient.originalName,
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxHeight()
        )
    }

}