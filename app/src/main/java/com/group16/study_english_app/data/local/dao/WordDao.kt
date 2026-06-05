package com.group16.study_english_app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.group16.study_english_app.data.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: WordEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<WordEntity>): List<Long>

    @Update
    suspend fun updateWord(word: WordEntity): Int

    @Delete
    suspend fun deleteWord(word: WordEntity): Int

    @Query("SELECT * FROM words WHERE deckId = :deckId ORDER BY word ASC")
    fun getWordsByDeckId(deckId: Long): Flow<List<WordEntity>>

    @Query("SELECT COUNT(*) FROM words WHERE deckId = :deckId")
    fun getWordsCountInDeck(deckId: Long): Flow<Int>

    @Query("SELECT * FROM words WHERE id = :id")
    suspend fun getWordById(id: Long): WordEntity?

    @Query("SELECT * FROM words WHERE id IN (:ids)")
    suspend fun getWordsByIds(ids: List<Long>): List<WordEntity>
}
