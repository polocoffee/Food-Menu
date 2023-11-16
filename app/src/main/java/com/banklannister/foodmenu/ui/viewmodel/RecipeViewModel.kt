package com.banklannister.foodmenu.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.banklannister.foodmenu.data.network.MealApiClient
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val _state = mutableStateOf<RecipeViewState>(RecipeViewState.Loading)
    val state: State<RecipeViewState> = _state

    fun processIntent(intent: RecipeViewIntent) {
        when (intent) {
            RecipeViewIntent.LoadRandomRecipe -> loadRandomRecipe()
            is RecipeViewIntent.SearchRecipe -> searchRecipe(intent.query)
        }
    }

    private fun loadRandomRecipe() {
        viewModelScope.launch {
            _state.value = RecipeViewState.Loading
            try {
                _state.value = RecipeViewState.Success(
                    MealApiClient.getRandomRecipes()
                )
            } catch (e: Exception) {
                _state.value = RecipeViewState.Error("Error fetching data")
            }
        }
    }

    private fun searchRecipe(query: String) {
        viewModelScope.launch {
            _state.value = RecipeViewState.Loading
            try {
                _state.value = RecipeViewState.Success(
                    MealApiClient.getSearchRecipes(query)
                )
            } catch (e: Exception) {
                _state.value = RecipeViewState.Error("Error fetching data")
            }
        }
    }

}