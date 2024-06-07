package com.example.recipeapppaparaproject.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeapppaparaproject.presentation.home.HomeScreen
import com.example.recipeapppaparaproject.presentation.login.LoginScreen
import com.example.recipeapppaparaproject.presentation.recipeDetail.RecipeDetailScreen
import com.example.recipeapppaparaproject.presentation.register.RegisterScreen
import com.example.recipeapppaparaproject.utils.Constants.RECIPE_ID

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screens.LoginScreen) {

        composable(Screens.HomeScreen) {
           HomeScreen(
                navController = navController
            )
        }

        composable("login_screen") {
            LoginScreen(navController = navController)
        }

        composable("register_screen") {
            RegisterScreen(navController = navController)
        }
        composable("home_screen") {
            HomeScreen(navController = navController)
        }

        composable(
            "${Screens.RECIPE_DETAIL}/{$RECIPE_ID}",
            arguments = listOf(navArgument(RECIPE_ID) {
                type = NavType.IntType
            })
        ) {
            RecipeDetailScreen(
                onBackMealsScreen = {
                    navController.popBackStack()
                }
            )

        }


    }
}