package com.example.movieapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController
        , startDestination = Screens.Home.route) {
        composable(
            route = Screens.Home.route){
            HomeScreen(navController)
        }
        composable(
            route = Screens.Detail.route){
            DetailScreen(navController)
        }

    }
}