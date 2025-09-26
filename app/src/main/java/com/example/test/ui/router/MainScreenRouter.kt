package com.example.test.ui.router

sealed class Screen(val route: String) {
	object Main: Screen("main_screen")
	object Detail: Screen("detail_screen")
}