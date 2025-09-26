package com.example.test.ui.content

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.test.presentation.CharacterAction
import com.example.test.presentation.CharactersViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersListScreen(
	modifier: Modifier = Modifier,
	onNavigateToDetails: (Int) -> Unit,
	viewModel: CharactersViewModel = koinViewModel(),
) {
	val state by viewModel.state.collectAsStateWithLifecycle()
	LaunchedEffect(Unit) {
		viewModel.handleAction(CharacterAction.GetCharacters)
	}
	val listState = rememberSaveable(saver = LazyListState.Saver) {
		LazyListState()
	}
	Box(
		modifier = modifier
			.fillMaxSize()
			.windowInsetsPadding(WindowInsets.statusBars)
	) {
		when {
			state.isLoading -> {
				CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
			}
			
			state.error != null -> {
				Column(
					modifier = Modifier.align(Alignment.Center),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					Text("Error: ${state.error}")
					Spacer(Modifier.height(8.dp))
					Button(onClick = { viewModel.handleAction(CharacterAction.GetCharacters) }) {
						Text("Retry")
					}
				}
			}
			
			state.charactersList.isNotEmpty() -> {
				LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
					items(items = state.charactersList, key = { it.id }) { character ->
						CharacterItem(
							character = character,
							onCLick = {
								onNavigateToDetails(character.id)
							}
						)
					}
				}
			}
		}
	}
}