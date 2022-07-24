package com.cezila.essential.presentation.drink_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cezila.essential.R
import com.cezila.essential.domain.model.Drink
import com.cezila.essential.domain.model.Ingredient
import com.cezila.essential.domain.model.IngredientType
import com.cezila.essential.presentation.DifficultyIcons
import com.cezila.essential.ui.theme.Nuosu
import com.cezila.essential.ui.theme.RatedColor
import com.cezila.essential.ui.theme.UnratedColor
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun IngredientTab(
    drink: Drink,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Ingredientes",
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = Nuosu,
            modifier = Modifier
                .padding(start = 30.dp, top = 10.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(drink.ingredients.size) { i ->
                IngredientItem(
                    ingredient = drink.ingredients[i],
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.align(CenterHorizontally)) {
            Icon(
                painter = painterResource(id = R.drawable.icon_circle),
                contentDescription = null,
                modifier = Modifier
                    .width(10.dp)
                    .height(10.dp)
                    .padding(2.dp),
                tint = RatedColor
            )
            Icon(
                painter = painterResource(id = R.drawable.icon_circle),
                contentDescription = null,
                modifier = Modifier
                    .width(10.dp)
                    .height(10.dp)
                    .padding(2.dp),
                tint = UnratedColor
            )
        }
    }

}