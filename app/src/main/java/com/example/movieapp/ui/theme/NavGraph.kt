package com.example.movieapp.ui.theme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController
        , startDestination = Screens.Home.route) {
        composable(
            route = Screens.Home.route){
            HomeScreen(navController)
        }
        composable(
            route = Screens.Detail.route,
            arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY){
                type = NavType.StringType
            })
        ){
          // Log.d("Argg",it.arguments?.getInt(DETAIL_ARGUMENT_KEY).toString())
            it.arguments?.getString("id")?.let { it1 -> DetailScreen(navController, it1) }
        }

    }
}