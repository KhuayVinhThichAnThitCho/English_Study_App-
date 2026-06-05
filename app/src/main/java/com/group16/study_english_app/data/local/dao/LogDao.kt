package com.group16.study_english_app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.group16.study_english_app.data.local.entity.ActivityLogEntity
import kotlinx.coroutines.flow.Flow

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
