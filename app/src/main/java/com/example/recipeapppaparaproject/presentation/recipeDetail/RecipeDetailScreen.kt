package com.example.recipeapppaparaproject.presentation.recipeDetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeapppaparaproject.data.model.RecipeDetailResponse.RecipeDetailResponse

@Composable
fun RecipeDetailScreen(recDetailViewModel: RecipeDetailViewModel = hiltViewModel(), onBackMealsScreen: () -> Unit) {
    val recipeDetailState by recDetailViewModel.selectRecipe.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Recipe Detail")
                },
                navigationIcon = {
                    IconButton(onClick = { onBackMealsScreen() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (recipeDetailState) {
                is RecipeDetailState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is RecipeDetailState.Success -> {
                    val recipeDetails = (recipeDetailState as RecipeDetailState.Success).meals
                    DetailCompose(recipeDetails)

                }
                is RecipeDetailState.Error -> {
                    Text(
                        text = (recipeDetailState as RecipeDetailState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun DetailCompose(
    recipeDetails: RecipeDetailResponse
){

}
