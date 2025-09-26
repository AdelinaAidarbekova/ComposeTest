package com.example.test.data.repository

import com.example.test.data.RickApi
import com.example.test.data.room.CharacterDao
import com.example.test.domain.RickRepository
import com.example.test.domain.model.CharacterInfoModel
import com.example.test.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class RickRepositoryImpl(
	private val api: RickApi,
	private val dao: CharacterDao,
) : RickRepository {
	override suspend fun getCharacters(): Flow<Result<List<CharacterModel>>> = flow {
		val cached = dao.getAllCharacters().firstOrNull()
		if (!cached.isNullOrEmpty()) emit(Result.success(cached.mapToDomain()))
		try {
			val remote = api.getCharactersList()
			dao.insertAll(remote.mapToDao())
			emit(Result.success(remote.mapToDomain()))
		} catch (e: Exception) {
			emit(Result.failure(Throwable(e.message)))
		}
	}
	
	override suspend fun getCharacterById(id: Int): Result<CharacterInfoModel> {
		return try {
			Result.success(api.getCharacterById(id).mapToDomain())
		} catch (e: Exception) {
			Result.failure(Throwable(e.message))
		}
	}
}