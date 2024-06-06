package com.example.recipeapppaparaproject.data.repo


import com.example.recipeapppaparaproject.data.model.RecipeDetailResponse.RecipeDetailResponse
import com.example.recipeapppaparaproject.data.model.RecipeResponse.RecipeResponse
import com.example.recipeapppaparaproject.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RecipeRepository {
    suspend fun getRecipes(): Flow<ApiResult<RecipeResponse>>
    suspend fun getRandomRecipes(): Flow<ApiResult<RecipeResponse>>
    suspend fun getRecipeDetails(id: Int): Flow<ApiResult<RecipeDetailResponse>>
    suspend fun getRecipesByCategory(query: String): Flow<ApiResult<RecipeResponse>>
}

