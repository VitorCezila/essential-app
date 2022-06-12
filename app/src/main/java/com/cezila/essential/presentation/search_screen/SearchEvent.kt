package com.cezila.essential.presentation.search_screen

sealed class SearchEvent{
    data class OnSearchQueryChange(val query: String): SearchEvent()
}
