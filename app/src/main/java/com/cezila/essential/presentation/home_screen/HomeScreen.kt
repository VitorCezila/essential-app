package com.cezila.essential.presentation.home_screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cezila.essential.R
import com.cezila.essential.presentation.DifficultyIcons
import com.cezila.essential.presentation.bottom_nav.BottomNavigationBar
import com.cezila.essential.presentation.bottom_nav.bottomNavItems
import com.cezila.essential.presentation.destinations.DrinkScreenDestination
import com.cezila.essential.presentation.destinations.SearchScreenDestination
import com.cezila.essential.ui.theme.BackgroundColor
import com.cezila.essential.ui.theme.Film
import com.cezila.essential.ui.theme.Nuosu
import com.cezila.essential.ui.theme.YellowSoft
import com.cezila.essential.util.Commom.route
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.gson.Gson
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
@Destination("home")
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {

    route = "home"
    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)
    val gson = Gson()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavItems,
                onItemClick = {
                    route = it.route
                    if (route != "home") navigator.navigate(route = route)
                }
            )
        }
    ) {
        when {
            (state.isError) -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.preguica),
                        contentDescription = null,
                        modifier = Modifier
                            .height(128.dp)
                            .width(128.dp)
                            .clip(CircleShape)
                    )

                    Text(
                        text = "Ocorreu um erro ao buscar os drinks!",
                        fontSize = 16.sp,
                        fontFamily = Nuosu,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        shape = RoundedCornerShape(50),
                        onClick = {
                            viewModel.onEvent(HomeEvent.Refresh)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = YellowSoft),
                        modifier = Modifier
                            .height(45.dp)
                            .width(240.dp)
                    ) {
                        Text(
                            text = "Tente novamente!",
                            fontSize = 14.sp,
                            fontFamily = Nuosu,
                            color = Color.White,
                            modifier = Modifier.width(220.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            (state.isLoading) -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = YellowSoft)
                }
            }

            (!state.isLoading) -> {
                val featuredRecipe = state.drinks.last()
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = {
                        viewModel.onEvent(HomeEvent.Refresh)
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(BackgroundColor)
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = 58.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                                .clickable {
                                    val drinkSerializable = gson.toJson(featuredRecipe)
                                    navigator.navigate(DrinkScreenDestination(drinkSerializable))
                                }
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(featuredRecipe.image)
                                    .build(),
                                contentDescription = null,
                                alignment = Alignment.Center,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .height(1027.dp)
                                    .width(680.dp),
                                colorFilter = ColorFilter.tint(Film, BlendMode.Multiply),
                                placeholder = painterResource(id = R.drawable.loading_png),
                                error = painterResource(id = R.drawable.image_not_found)
                            )

                            Column(
                                modifier = Modifier
                                    .padding(start = 15.dp)
                            ) {
                                Spacer(modifier = Modifier.height(30.dp))

                                Text(
                                    text = "Destaque",
                                    color = Color.White,
                                    fontFamily = Nuosu
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = featuredRecipe.name,
                                    fontFamily = Nuosu,
                                    fontSize = 40.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .width(200.dp)
                                )

                                DifficultyIcons(
                                    drink = featuredRecipe,
                                    modifier = Modifier.width(120.dp)
                                )
                            }

                            Text(
                                text = featuredRecipe.author,
                                fontFamily = Nuosu,
                                color = Color.White,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(start = 30.dp, bottom = 20.dp)
                                    .align(Alignment.BottomStart)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .padding(start = 25.dp, top = 25.dp)
                        ) {
                            Text(
                                text = "Em alta",
                                fontFamily = Nuosu,
                                color = Color.White,
                                fontSize = 22.sp
                            )

                            Spacer(modifier = Modifier.width(200.dp))

                            Text(
                                text = "Ver Todas",
                                fontFamily = Nuosu,
                                color = Color.Gray,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .clickable {
                                        route = "search"
                                        navigator.navigate(SearchScreenDestination)
                                    }
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        LazyRow(
                            modifier = Modifier
                                .fillMaxSize()
                                .fillMaxHeight()
                                .padding(start = 10.dp, end = 5.dp)
                        ) {
                            val todayRecipes =
                                if (state.drinks.isNotEmpty() && state.drinks.size >= 3) 3 else 0
                            items(todayRecipes) { i ->
                                val drink = state.drinks[i]
                                TodayDrinkItem(
                                    drink = drink,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(250.dp)
                                        .clickable {
                                            val drinkSerializable = gson.toJson(drink)
                                            navigator.navigate(
                                                DrinkScreenDestination(
                                                    drinkSerializable
                                                )
                                            )
                                        }
                                        .padding(start = 8.dp, bottom = 8.dp, end = 8.dp)
                                        .clip(
                                            RoundedCornerShape(
                                                topStart = 10.dp,
                                                topEnd = 10.dp,
                                                bottomStart = 10.dp,
                                                bottomEnd = 10.dp
                                            )
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}