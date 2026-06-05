package com.group16.study_english_app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.group16.study_english_app.data.local.entity.ProgressEntity
import kotlinx.coroutines.flow.Flow

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
