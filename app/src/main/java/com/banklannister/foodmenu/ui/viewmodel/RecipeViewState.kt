package com.banklannister.foodmenu.ui.viewmodel

import com.banklannister.foodmenu.data.model.Meal

sealed class RecipeViewState{
    object Loading: RecipeViewState()
    data class Success(val recipes: List<Meal>): RecipeViewState()
    data class Error(val message: String): RecipeViewState()
}
