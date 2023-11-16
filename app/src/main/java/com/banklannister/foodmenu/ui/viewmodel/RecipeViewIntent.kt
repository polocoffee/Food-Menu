package com.banklannister.foodmenu.ui.viewmodel

sealed class RecipeViewIntent{
    object LoadRandomRecipe: RecipeViewIntent()
    data class SearchRecipe(val query: String): RecipeViewIntent()
}
