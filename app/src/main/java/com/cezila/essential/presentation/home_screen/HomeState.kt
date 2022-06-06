package com.cezila.essential.presentation.home_screen

import com.cezila.essential.domain.model.Drink

data class HomeState(
    val drinks: List<Drink> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
