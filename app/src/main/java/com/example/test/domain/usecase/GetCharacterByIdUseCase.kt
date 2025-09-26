package com.example.test.domain.usecase

import com.example.test.domain.RickRepository
import com.example.test.domain.model.CharacterInfoModel

class GetCharacterByIdUseCase(
	private val repository: RickRepository,
) {
	suspend fun invoke(id: Int): Result<CharacterInfoModel> {
		return repository.getCharacterById(id)
	}
}