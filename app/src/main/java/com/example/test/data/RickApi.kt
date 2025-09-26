package com.example.test.data

import com.example.test.data.model.CharacterInfoDto
import com.example.test.data.model.ResultListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RickApi {
	@GET("character")
	suspend fun getCharactersList(): ResultListDto
	
	@GET("character/{id}")
	suspend fun getCharacterById(
		@Path("id")
		id: Int,
	): CharacterInfoDto
}