package com.example.test.domain

import com.example.test.domain.model.CharacterInfoModel
import com.example.test.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface RickRepository {
	suspend fun getCharacters() : Flow<Result<List<CharacterModel>>>
	
	suspend fun getCharacterById(id: Int): Result<CharacterInfoModel>
}