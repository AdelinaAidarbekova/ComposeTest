package com.example.test.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
	@Query("SELECT * FROM characters")
	fun getAllCharacters(): Flow<List<CharacterEntity>>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(characters: List<CharacterEntity>)
}