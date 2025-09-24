package com.example.test.domain.usecase

import com.example.test.domain.RickRepository
import com.example.test.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(private val repository: RickRepository) {
	suspend fun invoke(): Flow<Result<List<CharacterModel>>> {
		return repository.getCharacters()
	}
}