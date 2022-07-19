package com.cezila.essential.presentation.search_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cezila.essential.R
import com.cezila.essential.presentation.bottom_nav.BottomNavItem
import com.cezila.essential.presentation.bottom_nav.BottomNavigationBar
import com.cezila.essential.presentation.destinations.DrinkScreenDestination
import com.cezila.essential.ui.theme.BackgroundColor
import com.cezila.essential.ui.theme.GrayUnuse
import com.cezila.essential.util.Commom
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
    viewModel: SearchScreenViewModel = hiltViewModel()
) {

    route = "search"
    val gson = Gson()
    val state = viewModel.state

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(name = "Discover", route = "home", icon = R.drawable.flame),
                    BottomNavItem(name = "Search", route = "search", R.drawable.searchicons),
                    BottomNavItem(name = "About", route = "about", R.drawable.about_icon)
                ),
                onItemClick = {
                    Commom.route = it.route
                    navigator.navigate(route = Commom.route)
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
                            SearchEvent.OnSearchQueryChange(it)
                        )
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    placeholder = {
                        Text(text = "Search...", color = Color.White)
                    },
                    maxLines = 1,
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = GrayUnuse,
                        cursorColor = GrayUnuse
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
            }
        }
    }
}