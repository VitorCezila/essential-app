package com.cezila.essential.presentation.search_screen

import com.cezila.essential.domain.model.Drink

data class SearchState(
    val drinks: List<Drink> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)
