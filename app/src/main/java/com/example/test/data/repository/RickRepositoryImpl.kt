package com.example.test.data.repository

import com.example.test.data.RickApi
import com.example.test.domain.RickRepository
import com.example.test.domain.model.CharacterModel

class RickRepositoryImpl(private val api: RickApi) : RickRepository {
	override suspend fun getCharacters(): Result<List<CharacterModel>> {
		return try {
			val result = api.getCharactersList().mapToDomain()
			Result.success(result)
		} catch (e: Exception) {
			Result.failure(Throwable("Something went wrong"))
		}
	}
}