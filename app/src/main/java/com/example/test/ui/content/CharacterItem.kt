package com.example.test.ui.content

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.util.DebugLogger
import com.example.test.domain.model.CharacterModel

@Composable
fun CharacterItem(
	character: CharacterModel,
) {
	val context = LocalContext.current
	val imageLoader = ImageLoader.Builder(context)
		.logger(DebugLogger())
		.build()
	Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
		AsyncImage(
			model = character.image,
			contentDescription = character.name,
			modifier = Modifier.size(120.dp),
			imageLoader = imageLoader,
			contentScale = ContentScale.Crop
		)
		Spacer(modifier = Modifier.width(12.dp))
		Column {
			Text(text = character.name, style = MaterialTheme.typography.titleMedium)
			Text(text = "${character.status} - ${character.species}", style = MaterialTheme.typography.bodyMedium)
		}
	}
}