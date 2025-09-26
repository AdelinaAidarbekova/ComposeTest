package com.example.test.ui.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.test.ui.content.CharactersListScreen
import com.example.test.ui.content.DetailScreen

@Composable
fun NavigationStack() {
	val navController = rememberNavController()
	
	NavHost(navController = navController, startDestination = Screen.Main.route) {
		composable(route = Screen.Main.route) {
			CharactersListScreen(onNavigateToDetails = { characterId ->
				navController.navigate(route = Screen.Detail.route + "?id=$characterId")
			})
		}
		composable(
			route = Screen.Detail.route + "?id={id}",
			arguments = listOf(
				navArgument("id") {
					type = NavType.IntType
				}
			)
		) {
			DetailScreen(id = it.arguments?.getInt("id") ?: 0)
		}
	}
}