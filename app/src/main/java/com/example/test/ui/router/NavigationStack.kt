package com.example.test.ui.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.test.ui.content.CharactersListScreen
import com.example.test.ui.content.DetailScreen
import com.example.test.utils.Const.ID_ARGUMENT

@Composable
fun NavigationStack() {
	val navController = rememberNavController()
	
	NavHost(navController = navController, startDestination = Screen.Main.route) {
		composable(route = Screen.Main.route) {
			CharactersListScreen(onNavigateToDetails = { characterId ->
				navController.navigate(route = Screen.Detail.route + "?$ID_ARGUMENT=$characterId")
			})
		}
		composable(
			route = Screen.Detail.route + "?$ID_ARGUMENT={$ID_ARGUMENT}",
			arguments = listOf(
				navArgument(ID_ARGUMENT) {
					type = NavType.IntType
				}
			)
		) {
			DetailScreen(
				id = it.arguments?.getInt(ID_ARGUMENT) ?: 0,
				onBack = { navController.popBackStack() })
		}
	}
}