package com.example.test.data.repository

import com.example.test.data.model.ResultListDto
import com.example.test.domain.model.CharacterModel

fun ResultListDto.mapToDomain() : List<CharacterModel> {
	return this.results.map { result ->
		CharacterModel(
			id = result.id,
			image = result.image,
			name = result.name,
			species = result.species,
			status = result.status
		)
	}
}