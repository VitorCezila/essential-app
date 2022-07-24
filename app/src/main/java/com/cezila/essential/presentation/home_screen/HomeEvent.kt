package com.cezila.essential.presentation.home_screen


sealed class HomeEvent {
    object Refresh: HomeEvent()
    data class OnSearchQueryChange(val query: String): HomeEvent()
}