package com.example.recipeapppaparaproject.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeapppaparaproject.utils.Constants.MEAL_ID

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
       /* composable(Screens.HomeScreen.route) {
           HomeScreen(
                navController = navController
            )
        }

        composable(
            Screens.MealDetails.route,
            arguments = listOf(navArgument(MEAL_ID) {
                type = NavType.IntType
            })
        ) {
            DetailScreen(
                onBackMealsScreen = {
                    navController.popBackStack()
                }
            )
        }*/
    }
}