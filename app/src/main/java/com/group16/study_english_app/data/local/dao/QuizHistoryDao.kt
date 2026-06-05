package com.group16.study_english_app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.group16.study_english_app.data.local.entity.QuizHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizHistory(history: QuizHistoryEntity): Long

    @Query("SELECT * FROM quiz_history WHERE userId = :userId ORDER BY dateString DESC")
    fun getQuizHistory(userId: Long): Flow<List<QuizHistoryEntity>>

    @Query("SELECT * FROM quiz_history WHERE userId = :userId")
    suspend fun getQuizHistorySync(userId: Long): List<QuizHistoryEntity>
}
