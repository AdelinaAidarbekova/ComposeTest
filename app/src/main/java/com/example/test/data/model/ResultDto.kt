package com.example.test.data.model

data class ResultListDto(
    val results: List<ResultDto>
)

data class ResultDto(
	val id: Int,
	val image: String,
	val name: String,
	val species: String,
	val status: String
)