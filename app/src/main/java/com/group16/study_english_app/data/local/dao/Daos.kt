package com.group16.study_english_app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.group16.study_english_app.data.local.entity.ActivityLogEntity
import com.group16.study_english_app.data.local.entity.DeckEntity
import com.group16.study_english_app.data.local.entity.ProgressEntity
import com.group16.study_english_app.data.local.entity.QuizHistoryEntity
import com.group16.study_english_app.data.local.entity.UserEntity
import com.group16.study_english_app.data.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserEntity): Long

    @Update
    suspend fun updateUser(user: UserEntity): Int

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Long): UserEntity?

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserByIdFlow(id: Long): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?
}

@Dao
interface DeckDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeck(deck: DeckEntity): Long

    @Update
    suspend fun updateDeck(deck: DeckEntity): Int

    @Delete
    suspend fun deleteDeck(deck: DeckEntity): Int

    @Query("SELECT * FROM decks WHERE userId = :userId ORDER BY name ASC")
    fun getDecksByUserId(userId: Long): Flow<List<DeckEntity>>

    @Query("SELECT * FROM decks WHERE id = :id")
    suspend fun getDeckById(id: Long): DeckEntity?
}

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

@Dao
interface ProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: ProgressEntity): Long

    @Update
    suspend fun updateProgress(progress: ProgressEntity): Int

    @Query("SELECT * FROM learning_progress WHERE wordId = :wordId LIMIT 1")
    suspend fun getProgressByWordId(wordId: Long): ProgressEntity?

    @Query("SELECT * FROM learning_progress WHERE userId = :userId")
    fun getProgressForUser(userId: Long): Flow<List<ProgressEntity>>

    @Query("SELECT * FROM learning_progress WHERE userId = :userId AND wordId = :wordId LIMIT 1")
    suspend fun getProgressByWordAndUser(userId: Long, wordId: Long): ProgressEntity?

    @Query("SELECT COUNT(*) FROM learning_progress WHERE userId = :userId AND status != 'NEW'")
    fun getLearnedWordsCount(userId: Long): Flow<Int>

    @Query("SELECT COUNT(*) FROM learning_progress WHERE userId = :userId AND nextReviewTimeMs <= :currentTimeMs AND status != 'NEW'")
    fun getDueWordsCount(userId: Long, currentTimeMs: Long): Flow<Int>

    @Query("SELECT * FROM learning_progress WHERE userId = :userId")
    suspend fun getAllProgressForUserSync(userId: Long): List<ProgressEntity>
}

@Dao
interface LogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: ActivityLogEntity): Long

    @Update
    suspend fun updateLog(log: ActivityLogEntity): Int

    @Query("SELECT * FROM activity_logs WHERE userId = :userId AND dateString = :dateString AND actionType = :actionType LIMIT 1")
    suspend fun getLogByDateAndType(userId: Long, dateString: String, actionType: String): ActivityLogEntity?

    @Query("SELECT * FROM activity_logs WHERE userId = :userId ORDER BY dateString DESC")
    fun getLogsForUser(userId: Long): Flow<List<ActivityLogEntity>>

    @Query("SELECT * FROM activity_logs WHERE userId = :userId ORDER BY dateString DESC LIMIT :limit")
    fun getRecentLogs(userId: Long, limit: Int): Flow<List<ActivityLogEntity>>

    @Query("SELECT * FROM activity_logs WHERE userId = :userId AND dateString >= :sinceDate ORDER BY dateString ASC")
    fun getLogsSinceDate(userId: Long, sinceDate: String): Flow<List<ActivityLogEntity>>
}

@Dao
interface QuizHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizHistory(history: QuizHistoryEntity): Long

    @Query("SELECT * FROM quiz_history WHERE userId = :userId ORDER BY dateString DESC")
    fun getQuizHistory(userId: Long): Flow<List<QuizHistoryEntity>>

    @Query("SELECT * FROM quiz_history WHERE userId = :userId")
    suspend fun getQuizHistorySync(userId: Long): List<QuizHistoryEntity>
}
