package com.example.test.presentation.details

sealed interface CharacterDetailsAction {
	data class LoadCharacter(val id: Int): CharacterDetailsAction
}