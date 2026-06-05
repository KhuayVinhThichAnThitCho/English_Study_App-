package com.group16.study_english_app.data.repository

import com.group16.study_english_app.data.local.dao.LogDao
import com.group16.study_english_app.data.local.dao.ProgressDao
import com.group16.study_english_app.data.local.dao.QuizHistoryDao
import com.group16.study_english_app.data.local.dao.WordDao
import com.group16.study_english_app.data.local.entity.ActivityLogEntity
import com.group16.study_english_app.data.local.entity.ProgressEntity
import com.group16.study_english_app.data.local.entity.QuizHistoryEntity
import com.group16.study_english_app.data.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LearningRepository(
    private val progressDao: ProgressDao,
    private val wordDao: WordDao,
    private val logDao: LogDao,
    private val quizHistoryDao: QuizHistoryDao
) {
    fun getLearnedWordsCount(userId: Long): Flow<Int> {
        return progressDao.getLearnedWordsCount(userId)
    }

    fun getDueWordsCount(userId: Long, currentTimeMs: Long): Flow<Int> {
        return progressDao.getDueWordsCount(userId, currentTimeMs)
    }

    fun getActivityLogs(userId: Long): Flow<List<ActivityLogEntity>> {
        return logDao.getLogsForUser(userId)
    }

    fun getRecentActivityLogs(userId: Long, limit: Int): Flow<List<ActivityLogEntity>> {
        return logDao.getRecentLogs(userId, limit)
    }

    fun getActivityLogsSinceDate(userId: Long, sinceDate: String): Flow<List<ActivityLogEntity>> {
        return logDao.getLogsSinceDate(userId, sinceDate)
    }

    fun getQuizHistory(userId: Long): Flow<List<QuizHistoryEntity>> {
        return quizHistoryDao.getQuizHistory(userId)
    }

    suspend fun insertQuizHistory(userId: Long, correctCount: Int, totalCount: Int) {
        val today = getTodayString()
        val history = QuizHistoryEntity(
            userId = userId,
            dateString = today,
            correctCount = correctCount,
            totalCount = totalCount
        )
        quizHistoryDao.insertQuizHistory(history)
    }

    /**
     * Gets a list of words that have not been learned yet (Status = NEW)
     */
    suspend fun getNewWords(userId: Long, limit: Int): List<WordEntity> {
        val allProgress = progressDao.getAllProgressForUserSync(userId)
        val newWordIds = allProgress
            .filter { it.status == "NEW" }
            .take(limit)
            .map { it.wordId }
        
        return if (newWordIds.isNotEmpty()) {
            wordDao.getWordsByIds(newWordIds)
        } else {
            emptyList()
        }
    }

    /**
     * Gets a list of words that are due for review (Status != NEW and nextReviewTimeMs <= currentTimeMs)
     */
    suspend fun getDueWords(userId: Long, currentTimeMs: Long, limit: Int): List<WordEntity> {
        val allProgress = progressDao.getAllProgressForUserSync(userId)
        val dueWordIds = allProgress
            .filter { it.status != "NEW" && it.nextReviewTimeMs <= currentTimeMs }
            .take(limit)
            .map { it.wordId }
        
        return if (dueWordIds.isNotEmpty()) {
            wordDao.getWordsByIds(dueWordIds)
        } else {
            emptyList()
        }
    }

    /**
     * Updates progress for a word using the SuperMemo-2 algorithm.
     * feedback: 1 = Again, 3 = Hard, 4 = Good, 5 = Easy
     */
    suspend fun updateSrsProgress(
        userId: Long,
        wordId: Long,
        feedback: Int,
        isDebugMinutes: Boolean
    ) {
        val currentTimeMs = System.currentTimeMillis()
        val progress = progressDao.getProgressByWordAndUser(userId, wordId) 
            ?: ProgressEntity(userId = userId, wordId = wordId, status = "NEW")
        
        val isFirstTime = progress.status == "NEW"

        // Quality: 1 = Again, 3 = Hard, 4 = Good, 5 = Easy
        var reps = progress.repetitions
        var ef = progress.easeFactor
        var interval = progress.intervalDays

        if (feedback < 3) {
            // Again (forgot the word)
            reps = 0
            interval = 1
        } else {
            // Correct recall
            if (reps == 0) {
                interval = 1
            } else if (reps == 1) {
                interval = if (isDebugMinutes) 3 else 6
            } else {
                interval = Math.round(interval * ef).toInt()
            }
            reps += 1
        }

        // Standard SM-2 ease factor formula
        ef += 0.1 - (5 - feedback) * (0.08 + (5 - feedback) * 0.02)
        if (ef < 1.3) {
            ef = 1.3
        }

        // Calculate next review time
        val nextReviewMs = if (isDebugMinutes) {
            currentTimeMs + interval * 60 * 1000L // Interval in minutes
        } else {
            currentTimeMs + interval * 24 * 60 * 60 * 1000L // Interval in days
        }

        val nextStatus = when {
            feedback < 3 -> "LEARNING"
            feedback == 5 && reps >= 3 -> "MASTERED"
            else -> "REVIEW"
        }

        val updatedProgress = progress.copy(
            easeFactor = ef,
            repetitions = reps,
            intervalDays = interval,
            nextReviewTimeMs = nextReviewMs,
            lastReviewedTimeMs = currentTimeMs,
            status = nextStatus
        )

        progressDao.insertProgress(updatedProgress)

        // Log study activity
        logActivity(userId, if (isFirstTime) "LEARNED" else "REVIEWED")
    }

    private suspend fun logActivity(userId: Long, actionType: String) {
        val today = getTodayString()
        val log = logDao.getLogByDateAndType(userId, today, actionType)
        if (log != null) {
            logDao.updateLog(log.copy(count = log.count + 1))
        } else {
            logDao.insertLog(
                ActivityLogEntity(
                    userId = userId,
                    dateString = today,
                    actionType = actionType,
                    count = 1
                )
            )
        }
    }

    private fun getTodayString(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }
}
