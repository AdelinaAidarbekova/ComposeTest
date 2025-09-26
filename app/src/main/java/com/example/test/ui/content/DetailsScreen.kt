package com.example.test.ui.content

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.test.presentation.details.CharacterDetailsAction
import com.example.test.presentation.details.CharacterDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
	id: Int,
	viewModel: CharacterDetailsViewModel = koinViewModel(),
	onBack: () -> Unit = {},
) {
	val state by viewModel.state.collectAsStateWithLifecycle()
	
	LaunchedEffect(Unit) {
		viewModel.handleAction(CharacterDetailsAction.LoadCharacter(id))
	}
	Box(
		modifier = Modifier.fillMaxSize()
	) {
		when {
			state.isLoading -> {
				CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
			}
			
			state.errorMessage != null -> {
				Column(
					modifier = Modifier.align(Alignment.Center),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					Text("Error: ${state.errorMessage}")
					Spacer(Modifier.height(8.dp))
					Button(onClick = {
						viewModel.handleAction(CharacterDetailsAction.LoadCharacter(id))
					}) {
						Text("Retry")
					}
				}
			}
			
			state.characterInfo != null -> {
				CharacterInfoContent(state.characterInfo!!, onBack = onBack)
			}
		}
	}
}