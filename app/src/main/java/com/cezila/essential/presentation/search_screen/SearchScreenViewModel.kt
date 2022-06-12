package com.cezila.essential.presentation.search_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cezila.essential.domain.repository.EssentialRepository
import com.cezila.essential.presentation.home_screen.HomeEvent
import com.cezila.essential.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val repository: EssentialRepository
) : ViewModel() {

    var state by mutableStateOf(SearchState())
    private var searchJob: Job? = null

    init {
        getDrinks()
    }

    fun onEvent(event: SearchEvent) {
        when(event){
            is SearchEvent.OnSearchQueryChange -> {
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
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            repository
                .getDrinkListings(fetchFromRemote, query)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    drinks = listings
                                )
                                Log.d("HomeScreen", "Success: ${state.drinks}")
                            }
                        }
                        is Resource.Loading -> {
                            Log.d("HomeScreen", "isLoading: ${result.isLoading}")
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Error -> {
                            getDrinks()
                            Log.d("HomeScreen", "Erro: ${result.message}")
                        }
                    }
                }
        }
    }

}