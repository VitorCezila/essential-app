package com.cezila.essential.presentation.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cezila.essential.domain.repository.EssentialRepository
import com.cezila.essential.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: EssentialRepository
) : ViewModel() {

    var state by mutableStateOf(HomeState())
    private var searchJob: Job? = null

    init {
        getDrinks()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Refresh -> {
                getDrinks(fetchFromRemote = true)
            }
            is HomeEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getDrinks()
                }
            }
        }
    }

    private fun getDrinks(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        state = state.copy(isLoading = true, isError = false)
        viewModelScope.launch {
            repository
                .getDrinkListings(fetchFromRemote, query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    drinks = listings,
                                    isError = false
                                )
                            }
                        }
                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = result.isLoading,
                                isError = false
                            )
                        }
                        is Resource.Error -> {
                            state = state.copy(isError = true)
                        }
                    }
                }
        }
    }
}