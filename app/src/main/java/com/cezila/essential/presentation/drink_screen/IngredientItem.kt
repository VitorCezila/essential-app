package com.cezila.essential.presentation.drink_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cezila.essential.domain.model.Ingredient
import com.cezila.essential.ui.theme.Nuosu
import com.cezila.essential.ui.theme.Orange
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun IngredientItem(
    modifier: Modifier = Modifier,
    ingredient: Ingredient
) {
    FlowRow(modifier = modifier) {
        Text(
            text = "${ingredient.originalName}",
            color = Orange,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxHeight(),
            fontFamily = Nuosu
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "${ingredient.name}",
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = Nuosu
        )
    }

}