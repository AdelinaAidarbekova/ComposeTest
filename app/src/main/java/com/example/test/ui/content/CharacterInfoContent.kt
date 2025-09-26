package com.example.test.ui.content

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.test.domain.model.CharacterInfoModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterInfoContent(
	character: CharacterInfoModel,
	onBack: () -> Unit = {},
) {
	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text(character.name) },
				navigationIcon = {
					IconButton(onClick = onBack) {
						Icon(
							imageVector = Icons.Default.ArrowBack,
							contentDescription = "Back"
						)
					}
				}
			)
		}
	) { innerPadding ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.verticalScroll(rememberScrollState())
				.padding(innerPadding)
		) {
			// Character Image
			AsyncImage(
				model = ImageRequest.Builder(LocalContext.current)
					.data(character.image)
					.crossfade(true)
					.build(),
				contentDescription = character.name,
				modifier = Modifier
					.fillMaxWidth()
					.height(320.dp)
					.clip(RoundedCornerShape(24.dp)),
				contentScale = ContentScale.Crop
			)
			
			Spacer(modifier = Modifier.height(16.dp))
			
			// Name
			Text(
				text = character.name,
				style = MaterialTheme.typography.headlineSmall,
				modifier = Modifier.padding(horizontal = 16.dp)
			)
			
			Spacer(modifier = Modifier.height(8.dp))
			
			// Details section
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 16.dp),
				verticalArrangement = Arrangement.spacedBy(8.dp)
			) {
				InfoRow(label = "Status", value = character.status)
				InfoRow(label = "Species", value = character.species)
				InfoRow(label = "Type", value = character.type)
				InfoRow(label = "Gender", value = character.gender)
				InfoRow(label = "Location", value = character.location)
				InfoRow(label = "Origin", value = character.origin)
			}
			
			Spacer(modifier = Modifier.height(24.dp))
		}
	}
}

@Composable
fun InfoRow(label: String, value: String) {
	Row(
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		Text(
			text = "$label:",
			style = MaterialTheme.typography.bodyMedium,
			color = MaterialTheme.colorScheme.onSurfaceVariant
		)
		Text(
			text = value.ifEmpty { "Unknown" },
			style = MaterialTheme.typography.bodyMedium,
			fontWeight = FontWeight.Bold
		)
	}
}