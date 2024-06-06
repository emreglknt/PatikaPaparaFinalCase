package com.example.recipeapppaparaproject.data.api

import com.example.recipeapppaparaproject.data.model.RecipeDetailResponse.RecipeDetailResponse
import com.example.recipeapppaparaproject.data.model.RecipeResponse.RecipeResponse
import com.example.recipeapppaparaproject.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {

    //random recipes
    @GET("recipes/random")
    suspend fun getRandomRecipe(
        @Query("apiKey") apiKey: String=API_KEY,
    ):Response<RecipeResponse>

    //search recipes
    @GET("recipes/complexSearch")
    suspend fun getAllRecipes(
        @Query("number") number: Int = 70,
        @Query("offset") offset: Int = 0,
        @Query("apiKey") apiKey: String=API_KEY,
    ):Response<RecipeResponse>

    //categorized recipe
    @GET("recipes/complexSearch")
    suspend fun getRecipesByCategory(
        @Query("query") query: String,
        @Query("number") number: Int = 40,
        @Query("offset") offset: Int = 0,
        @Query("apiKey") apiKey: String=API_KEY,
    ):Response<RecipeResponse>




    //recipe details
    @GET("recipes/{id}/information")
    suspend fun getRecipeDetailsById(
        @Path("id") id: Int,
        @Query ("apiKey") apiKey: String=API_KEY,
    ):Response<RecipeDetailResponse>





}