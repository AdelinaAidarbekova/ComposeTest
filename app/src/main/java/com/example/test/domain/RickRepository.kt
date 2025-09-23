package com.example.test.domain

import com.example.test.domain.model.CharacterModel

interface RickRepository {
	suspend fun getCharacters() : Result<List<CharacterModel>>
}