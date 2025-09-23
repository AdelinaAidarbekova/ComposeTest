package com.example.test.presentation

sealed interface CharacterAction {
	data object GetCharacters: CharacterAction
}