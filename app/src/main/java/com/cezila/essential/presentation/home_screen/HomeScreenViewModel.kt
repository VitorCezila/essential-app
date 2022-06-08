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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: EssentialRepository
): ViewModel() {

    var state by mutableStateOf(HomeState())

    init {
        getDrinks()
    }

    fun onEvent(event: HomeEvent) {
        when(event){
            is HomeEvent.Refresh -> {
                getDrinks(fetchFromRemote = true)
            }
        }
    }

    private fun getDrinks(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = true
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
                                    isLoading = false,
                                    drinks = listings
                                )
                                Log.d("Teste", "State: ${state.drinks}")
                            }
                        }
                        is Resource.Loading -> {
                            Log.d("##Resource Loading", "isLoading: ${result.isLoading}")
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Error -> {
                            result.message
                            Log.d("##Teste", "Tratar futuramente")
                        }
                    }
                }
        }
    }

}