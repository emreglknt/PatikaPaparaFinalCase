package com.example.recipeapppaparaproject.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapppaparaproject.data.model.RecipeResponse.RecipeResponse
import com.example.recipeapppaparaproject.data.repo.RecipeRepository
import com.example.recipeapppaparaproject.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RecipeState {
    data object Loading : RecipeState()
    data class Success(val recipes: RecipeResponse) : RecipeState()
    data class Error(val message: String) : RecipeState()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {
    private val _recipeState = MutableStateFlow<RecipeState>(RecipeState.Loading)
    val recipeState: StateFlow<RecipeState> = _recipeState

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            repository.getRecipes().collect { result ->
                when (result) {
                    is ApiResult.Success -> {
                        _recipeState.value = RecipeState.Success(result.data!!)
                    }
                    is ApiResult.Error -> {
                        Log.e("HomeViewModel", "Error fetching recipes: ${result.message}")
                        _recipeState.value = RecipeState.Error("Error fetching recipes: ${result.message}")
                    }
                    is ApiResult.Loading -> {
                        _recipeState.value = RecipeState.Loading
                    }
                }
            }
        }
    }

    fun getRecipesByCategory(query: String) {
        viewModelScope.launch {
            repository.getRecipesByCategory(query).collect {
                when (it) {
                    is ApiResult.Success -> {
                        _recipeState.value = RecipeState.Success(it.data!!)
                    }
                    is ApiResult.Error -> {
                        Log.e("HomeViewModel", "Error fetching recipes: ${it.message}")
                        _recipeState.value = RecipeState.Error("Error fetching recipes: ${it.message}")
                    }
                    is ApiResult.Loading -> {
                        _recipeState.value = RecipeState.Loading
                    }
                }
            }
        }
    }
}
