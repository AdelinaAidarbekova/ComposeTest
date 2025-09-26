package com.example.test.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.usecase.GetCharacterByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
	private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
) : ViewModel() {
	private val _state = MutableStateFlow(CharacterDetailsState())
	val state: StateFlow<CharacterDetailsState> = _state
	
	fun handleAction(action: CharacterDetailsAction) {
		when (action) {
			is CharacterDetailsAction.LoadCharacter -> loadCharacter(action.id)
		}
	}
	
	private fun loadCharacter(id: Int) {
		viewModelScope.launch {
			_state.update { it.copy(isLoading = true) }
			getCharacterByIdUseCase.invoke(id)
				.onSuccess { result ->
					_state.update { it.copy(isLoading = false, characterInfo = result) }
				}.onFailure { error ->
					_state.update { it.copy(isLoading = false, errorMessage = error.message) }
				}
		}
	}
}