package com.example.test.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CharactersViewModel(
	private val getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {
	private val _state = MutableStateFlow(CharactersState())
	val state: StateFlow<CharactersState> = _state
	
	fun handleAction(action: CharacterAction) {
		when (action) {
			CharacterAction.GetCharacters -> loadCharacters()
		}
	}
	
	private fun loadCharacters() {
		viewModelScope.launch(Dispatchers.IO) {
			_state.update { it.copy(isLoading = true, error = null) }
			getCharactersUseCase.invoke().collect { result ->
				result.onSuccess {
					_state.update {
						it.copy(
							isLoading = false,
							charactersList = result.getOrNull().orEmpty()
						)
					}
				}
				result.onFailure { error ->
					_state.update { it.copy(isLoading = false, error = error.message) }
				}
			}
		}
	}
	
}