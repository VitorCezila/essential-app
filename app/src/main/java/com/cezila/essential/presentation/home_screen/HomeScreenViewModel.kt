package com.cezila.essential.presentation.home_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
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

    init {
        getDrinks()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Refresh -> {
                getDrinks(fetchFromRemote = true)
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
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    drinks = listings,
                                    isError = false
                                )
                                Log.d("HomeScreen", "Success: ${state.drinks}")
                            }
                        }
                        is Resource.Loading -> {
                            Log.d("HomeScreen", "isLoading: ${result.isLoading}")
                            state = state.copy(
                                isLoading = result.isLoading,
                                isError = false
                            )
                        }
                        is Resource.Error -> {
                            state = state.copy(isError = true)
                            Log.d("HomeScreen", "Erro: ${result.message}")
                        }
                    }
                }
        }
    }
}