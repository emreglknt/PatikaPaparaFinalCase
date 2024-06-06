package com.example.recipeapppaparaproject.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.recipeapppaparaproject.R
import com.example.recipeapppaparaproject.data.model.RecipeResponse.Result

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    var searchText by remember { mutableStateOf("") }
    val recipeState by homeViewModel.recipeState.collectAsState()
    val categories = listOf("Breakfast","Dinner","Dessert","Pasta","Soups","Salads","Beef","Chicken")

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            SearchBar(searchText) {
                searchText = it
                homeViewModel.getRecipesByCategory(it)
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    CategoryCard(category) {
                        homeViewModel.getRecipesByCategory(category)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (recipeState) {
                is RecipeState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                is RecipeState.Success -> {
                    val recipes = (recipeState as RecipeState.Success).recipes.results

                    if (!recipes.isNullOrEmpty()) {
                        Log.e("RecipeState", "Recipe list size: ${recipes.size}")
                        recipes.forEachIndexed { index, recipe ->
                            Log.e("RecipeState", "Recipe $index: $recipe")
                        }

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(recipes.size) { index ->
                                RecipeCard(recipes[index])
                            }
                        }
                    } else {
                        Log.e("HomeScreen", "Recipes list is empty or null")
                        Text(
                            text = "No recipes found.",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
                is RecipeState.Error -> {
                    Text(
                        text = (recipeState as RecipeState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

            }
        }
    }
}

@Composable
fun SearchBar(searchText: String, onTextChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {

        BasicTextField(
            value = searchText,
            onValueChange = onTextChange,
            textStyle = TextStyle(color = Color.Black),
            singleLine = true,
            cursorBrush = SolidColor(Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .background(Color.Transparent, shape = RoundedCornerShape(16.dp))
                .padding(vertical = 12.dp)
        ) {
            if (searchText.isEmpty()) {
                Text(
                    text = "Search recipes",
                    style = TextStyle(color = Color.Gray)
                )
            }
            it()
        }
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.padding(end = 5.dp)
        )

    }
}

@Composable
fun CategoryCard(category: String, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(5.dp)
            .clickable { onClick() }
            .clip(RoundedCornerShape(10.dp)) // Rounded corners
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFFFF8C61), Color(0xFF5C374C)),
                    startX = 0f,
                    endX = 1000f
                )
            )

            .size(130.dp,50.dp)
    ) {
        Text(
            text = category,
            color = Color.White,
            fontSize = 13.sp,
            // bold text
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(10.dp)
        )
    }
}



@Composable
fun RecipeCard(recipe: Result) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(8.dp)
        ) {
            Image(
                painter = rememberImagePainter(recipe.image),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = recipe.title,
                    fontSize = 15.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = Color.Green,
        contentColor = Color.White
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Search") },
            label = { Text("Home") },
            selected = false,
            onClick = { /* Handle search click */ }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
            label = { Text("Favorites") },
            selected = false,
            onClick = { /* Handle favorites click */ }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.ExitToApp, contentDescription = "Logout") },
            label = { Text("Logout") },
            selected = false,
            onClick = { /* Handle profile click */ }
        )
    }
}