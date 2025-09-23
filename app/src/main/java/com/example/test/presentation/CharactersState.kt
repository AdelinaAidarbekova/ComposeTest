package com.example.test.presentation

import com.example.test.domain.model.CharacterModel

data class CharactersState (
	val isLoading: Boolean = false,
	val error: String? = null,
	val charactersList: List<CharacterModel> = emptyList(),
)