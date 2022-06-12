package com.cezila.essential.presentation.drink_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cezila.essential.R
import com.cezila.essential.domain.model.Drink
import com.cezila.essential.domain.model.Ingredient
import com.cezila.essential.domain.model.IngredientType
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
    Column(modifier = modifier) {
        Text(
            text = "Ingredients",
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = Nuosu,
            modifier = Modifier
                .padding(start = 30.dp, top = 10.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column {
            Row(
                modifier = Modifier
                    .padding(start = 30.dp, top = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .width(150.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = "Alcohol:",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = Nuosu
                    )

                    LazyColumn {
                        val alcoholIngredients = arrayListOf<Ingredient>()
                        for(i in 0 until drink.ingredients.size) {
                            val isIngredientAlcohol = drink.ingredients[i].ingredientType == IngredientType.Alcohol
                            if(isIngredientAlcohol) alcoholIngredients.add(drink.ingredients[i])
                        }
                        items(alcoholIngredients.size) { i ->
                            IngredientItem(
                                ingredient = alcoholIngredients[i]
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(30.dp))

                Column(
                    modifier = Modifier
                        .width(150.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = "Garnish:",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = Nuosu
                    )
                    LazyColumn {
                        val garnishIngredients = arrayListOf<Ingredient>()
                        for(i in 0 until drink.ingredients.size) {
                            val isIngredientGarnish = drink.ingredients[i].ingredientType == IngredientType.Garnish
                            if(isIngredientGarnish) garnishIngredients.add(drink.ingredients[i])
                        }
                        items(garnishIngredients.size) { i ->
                            IngredientItem(
                                ingredient = garnishIngredients[i]
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .padding(start = 30.dp, top = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .width(150.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = "Juice",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = Nuosu
                    )
                    LazyColumn {
                        val juiceIngredients = arrayListOf<Ingredient>()
                        for(i in 0 until drink.ingredients.size) {
                            val isIngredientJuice = drink.ingredients[i].ingredientType == IngredientType.Juice
                            if(isIngredientJuice) juiceIngredients.add(drink.ingredients[i])
                        }
                        items(juiceIngredients.size) { i ->
                            IngredientItem(
                                ingredient = juiceIngredients[i]
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(30.dp))

                Column(
                    modifier = Modifier
                        .width(150.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = "Extra",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = Nuosu
                    )
                    LazyColumn {
                        val extraIngredients = arrayListOf<Ingredient>()
                        for(i in 0 until drink.ingredients.size) {
                            val isIngredientExtra = drink.ingredients[i].ingredientType == IngredientType.Extra
                            if(isIngredientExtra) extraIngredients.add(drink.ingredients[i])
                        }
                        items(extraIngredients.size) { i ->
                            IngredientItem(
                                ingredient = extraIngredients[i]
                            )
                        }
                    }
                }
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