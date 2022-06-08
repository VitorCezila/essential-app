package com.cezila.essential.presentation.drink_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cezila.essential.domain.model.Drink
import com.cezila.essential.domain.model.Ingredient
import com.cezila.essential.domain.model.IngredientType
import com.cezila.essential.ui.theme.BackgroundColor
import com.cezila.essential.ui.theme.Film
import com.cezila.essential.ui.theme.Nuosu
import com.google.gson.Gson
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(route = "drink")
fun DrinkScreen(
    drinkSerializable: String,
    navigator: DestinationsNavigator
) {

    val gson = Gson()
    val drink = gson.fromJson(drinkSerializable, Drink::class.java)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
            contentAlignment = BottomStart
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(drink.image)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                colorFilter = ColorFilter.tint(Film, BlendMode.Multiply)
            )

            Text(
                text = drink.name,
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = Nuosu,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 30.dp, bottom = 40.dp)
                    .width(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // FAZER UM SCROLL QUE TROCA ENTRE INGREDIENTS E MODO DE PREPARO

        Column {
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
                            fontSize = 16.sp,
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
                            fontSize = 16.sp,
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
                            fontSize = 16.sp,
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
                            fontSize = 16.sp,
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
        }
    }
}