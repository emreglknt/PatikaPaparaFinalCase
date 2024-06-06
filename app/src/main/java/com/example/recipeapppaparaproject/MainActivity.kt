package com.example.recipeapppaparaproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapppaparaproject.presentation.home.HomeScreen
import com.example.recipeapppaparaproject.presentation.login.LoginScreen
import com.example.recipeapppaparaproject.presentation.register.RegisterScreen

import com.example.recipeapppaparaproject.ui.theme.RecipeAppPaparaProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeAppPaparaProjectTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize()) {
                    setupNavGraph(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun setupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login_screen",
    ) {
        /*composable("splash_screen") {
            SplashScreen(navController = navController)
        }*/

        composable("login_screen") {
            LoginScreen(navController = navController)
        }

        composable("register_screen") {
            RegisterScreen(navController = navController)
        }
        composable("home_screen") {
            HomeScreen(navController = navController)
        }


    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecipeAppPaparaProjectTheme {
        Greeting("Android")
    }
}