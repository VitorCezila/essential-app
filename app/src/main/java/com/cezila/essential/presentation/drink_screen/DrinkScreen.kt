package com.cezila.essential.presentation.drink_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cezila.essential.R
import com.cezila.essential.domain.model.Drink
import com.cezila.essential.presentation.DifficultyIcons
import com.cezila.essential.presentation.destinations.HomeScreenDestination
import com.cezila.essential.ui.theme.*
import com.cezila.essential.util.Commom.route
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.gson.Gson
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalPagerApi
@Composable
@Destination(route = "drink")
fun DrinkScreen(
    navigator: DestinationsNavigator,
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
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
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

            Column(
                modifier = Modifier
                    .padding(start = 15.dp)
            ) {
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = drink.name,
                    fontFamily = Nuosu,
                    fontSize = 40.sp,
                    color = Color.White,
                    modifier = Modifier
                        .width(200.dp)
                )

                DifficultyIcons(
                    drink = drink,
                    modifier = Modifier.width(200.dp),
                )
            }

            Text(
                text = drink.author,
                fontFamily = Nuosu,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 30.dp, bottom = 20.dp)
                    .align(Alignment.BottomStart)
            )

            FloatingActionButton(
                backgroundColor = BackgroundColor,
                modifier = Modifier
                    .padding(start = 335.dp, top = 20.dp)
                    .width(22.dp)
                    .height(22.dp),
                contentColor = GrayUnuse,
                onClick = {
                    route = "home"
                    navigator.navigate(HomeScreenDestination)
                },
                content = {
                    Icon(Icons.Filled.Close, "")
                })
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