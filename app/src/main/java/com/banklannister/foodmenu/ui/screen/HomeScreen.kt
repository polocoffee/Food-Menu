package com.banklannister.foodmenu.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.banklannister.foodmenu.ui.components.ErrorComponents
import com.banklannister.foodmenu.ui.components.LoadingComponents
import com.banklannister.foodmenu.ui.components.SuccessComponents
import com.banklannister.foodmenu.ui.viewmodel.RecipeViewIntent
import com.banklannister.foodmenu.ui.viewmodel.RecipeViewModel
import com.banklannister.foodmenu.ui.viewmodel.RecipeViewState

@Composable
fun HomeScreen(
    recipeViewModel: RecipeViewModel
) {
    val state by recipeViewModel.state

    when (state) {
        is RecipeViewState.Error -> {
            val message = (state as RecipeViewState.Error).message
            ErrorComponents(message = message, onRefreshClicked = {
                recipeViewModel.processIntent(RecipeViewIntent.LoadRandomRecipe)
            })
        }

        is RecipeViewState.Loading -> {
            LoadingComponents()
        }

        is RecipeViewState.Success -> {
            val recipes = (state as RecipeViewState.Success).recipes
            SuccessComponents(recipes = recipes, onSearchClicked = { query ->
                recipeViewModel.processIntent(RecipeViewIntent.SearchRecipe(query))
            })
        }
    }

    LaunchedEffect(Unit) {
        recipeViewModel.processIntent(RecipeViewIntent.LoadRandomRecipe)
    }

}