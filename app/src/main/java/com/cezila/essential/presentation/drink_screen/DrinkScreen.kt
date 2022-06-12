package com.cezila.essential.presentation.drink_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.cezila.essential.ui.theme.BackgroundColor
import com.cezila.essential.ui.theme.Film
import com.cezila.essential.ui.theme.Nuosu
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.gson.Gson
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalPagerApi
@Composable
@Destination(route = "drink")
fun DrinkScreen(
    drinkSerializable: String
) {

    val gson = Gson()
    val drink = gson.fromJson(drinkSerializable, Drink::class.java)
    val pagerState = rememberPagerState()

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

        HorizontalPager(
            verticalAlignment = Alignment.Top,
            count = 2,
            state = pagerState,
            modifier = Modifier.fillMaxHeight()
        ) { tabIndex ->
            when (tabIndex) {
                0 -> IngredientTab(drink = drink, Modifier.fillMaxHeight())
                1 -> StepTab(drink = drink, Modifier.fillMaxHeight())
            }
        }
    }
}