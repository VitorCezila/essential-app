package com.cezila.essential.presentation.drink_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.cezila.essential.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun IngredientTab(
    drink: Drink,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {

        Text(
            text = drink.description,
            modifier = Modifier
                .fillMaxWidth(),
            color = TextBeige,
            fontSize = 14.sp,
            fontFamily = Nuosu,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Divider(
            color = TextBeige,
            thickness = 1.dp,
            modifier = Modifier
                .padding(start = 130.dp, end = 130.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

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
                painter = painterResource(id = R.drawable.circle_icon),
                contentDescription = null,
                modifier = Modifier
                    .width(10.dp)
                    .height(10.dp)
                    .padding(2.dp),
                tint = RatedColor
            )
            Icon(
                painter = painterResource(id = R.drawable.circle_icon),
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