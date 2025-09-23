package com.example.test.domain.usecase

import com.example.test.domain.RickRepository
import com.example.test.domain.model.CharacterModel

class GetCharactersUseCase(private val repository: RickRepository) {
	suspend fun invoke(): Result<List<CharacterModel>> {
		return repository.getCharacters()
	}
}