package com.example.test.presentation.details

import com.example.test.domain.model.CharacterInfoModel

data class CharacterDetailsState(
	val isLoading: Boolean = false,
	val errorMessage: String? = null,
	val characterInfo: CharacterInfoModel? = null
)