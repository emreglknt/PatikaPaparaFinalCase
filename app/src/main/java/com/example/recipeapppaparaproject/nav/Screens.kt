package com.example.recipeapppaparaproject.nav


sealed class Screens(val route: String) {

    object HomeScreen : Screens("home")


    object MealDetails : Screens("MealDetails/{$mealId}"){
        fun passMealId(mealId: Int): String {
            return "MealDetails/$mealId"
        }
    }


}