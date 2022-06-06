package com.cezila.essential.presentation.home_screen


sealed interface HomeEvent {
    object Refresh: HomeEvent
}