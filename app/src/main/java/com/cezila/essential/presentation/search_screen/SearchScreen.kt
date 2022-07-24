package com.cezila.essential.presentation.search_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cezila.essential.presentation.bottom_nav.BottomNavigationBar
import com.cezila.essential.presentation.bottom_nav.bottomNavItems
import com.cezila.essential.presentation.destinations.DrinkScreenDestination
import com.cezila.essential.presentation.home_screen.HomeEvent
import com.cezila.essential.presentation.home_screen.HomeScreenViewModel
import com.cezila.essential.ui.theme.BackgroundColor
import com.cezila.essential.ui.theme.DarkBackground
import com.cezila.essential.ui.theme.GrayUnuse
import com.cezila.essential.ui.theme.YellowSoft
import com.cezila.essential.util.Commom.route
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.gson.Gson
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
@Destination("search")
fun SearchScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    route = "search"
    val gson = Gson()
    val state = viewModel.state

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavItems,
                onItemClick = {
                    route = it.route
                    if(route != "search") navigator.navigate(route = route)
                }
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
        ) {
            if (!state.isLoading) {
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = {
                        viewModel.onEvent(
                            HomeEvent.OnSearchQueryChange(it)
                        )
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    placeholder = {
                        Text(text = "Buscar...", color = Color.White)
                    },
                    maxLines = 1,
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = GrayUnuse,
                        cursorColor = GrayUnuse,
                        textColor = Color.White,
                        placeholderColor = Color.White,
                        unfocusedBorderColor = DarkBackground
                    )
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 58.dp)
                ) {
                    item {
                        FlowRow(
                            mainAxisAlignment = MainAxisAlignment.Start
                        ) {
                            val drinks = state.drinks
                            drinks.forEach {
                                SearchDrinkItem(
                                    drink = it,
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .clickable {
                                            val drinkSerializable = gson.toJson(it)
                                            navigator.navigate(
                                                DrinkScreenDestination(
                                                    drinkSerializable = drinkSerializable
                                                )
                                            )
                                        }
                                        .width(180.dp)
                                )
                            }
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = YellowSoft)
                }
            }
        }
    }
}