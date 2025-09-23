package com.example.test.data

import com.example.test.data.model.ResultListDto
import retrofit2.http.GET

interface RickApi {
	@GET("character")
	suspend fun getCharactersList() : ResultListDto
}