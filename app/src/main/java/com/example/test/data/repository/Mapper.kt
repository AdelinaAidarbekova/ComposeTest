package com.example.test.data.repository

import com.example.test.data.model.CharacterInfoDto
import com.example.test.data.model.ResultListDto
import com.example.test.data.room.CharacterEntity
import com.example.test.domain.model.CharacterInfoModel
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

fun ResultListDto.mapToDao() : List<CharacterEntity> {
	return this.results.map { result ->
		CharacterEntity(
			id = result.id,
			image = result.image,
			name = result.name,
			species = result.species,
			status = result.status
		)
	}
}

fun List<CharacterEntity>.mapToDomain() : List<CharacterModel> {
	return this.map { result ->
		CharacterModel(
			id = result.id,
			image = result.image,
			name = result.name,
			species = result.species,
			status = result.status
		)
	}
}

fun CharacterInfoDto.mapToDomain(): CharacterInfoModel {
	return CharacterInfoModel(
		gender = this.gender,
		id = this.id,
		image = this.image,
		location = this.location.name,
		name = this.name,
		origin = this.origin.name,
		species = this.species,
		status = this.status,
		type = this.type,
	)
}