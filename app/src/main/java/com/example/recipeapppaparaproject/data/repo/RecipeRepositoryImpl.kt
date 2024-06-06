package com.example.recipeapppaparaproject.data.repo

import com.example.recipeapppaparaproject.data.api.RecipeApi
import com.example.recipeapppaparaproject.data.model.RecipeDetailResponse.RecipeDetailResponse
import com.example.recipeapppaparaproject.data.model.RecipeResponse.RecipeResponse
import com.example.recipeapppaparaproject.utils.ApiResult
import com.example.recipeapppaparaproject.utils.apiFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
class RecipeRepositoryImpl @Inject constructor(
    private val api: RecipeApi,
    ) : RecipeRepository {
    override suspend fun getRecipes(): Flow<ApiResult<RecipeResponse>> {
        return apiFlow { api.getAllRecipes()}
    }

    override suspend fun getRandomRecipes(): Flow<ApiResult<RecipeResponse>>  {
        return apiFlow { api.getRandomRecipe() }

    }

    override suspend fun getRecipeDetails(id: Int): Flow<ApiResult<RecipeDetailResponse>> {
        return apiFlow { api.getRecipeDetailsById(id) }
    }

    //category + search
    override suspend fun getRecipesByCategory(query: String): Flow<ApiResult<RecipeResponse>> {
       return apiFlow { api.getRecipesByCategory(query) }
    }


}