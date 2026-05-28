package com.group16.study_english_app.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.group16.study_english_app.data.local.AppDatabase
import com.group16.study_english_app.data.preferences.UserSessionDataStore
import com.group16.study_english_app.data.repository.LearningRepository
import com.group16.study_english_app.data.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull
import java.util.concurrent.TimeUnit

class ReminderWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val context = applicationContext
        
        // Initialize dependencies
        val database = AppDatabase.getDatabase(context)
        val sessionDataStore = UserSessionDataStore(context)
        val vocabularyRepository = com.group16.study_english_app.data.repository.VocabularyRepository(
            database.deckDao(),
            database.wordDao(),
            database.progressDao()
        )
        val userRepository = UserRepository(
            database.userDao(),
            sessionDataStore,
            vocabularyRepository
        )
        val learningRepository = LearningRepository(
            database.progressDao(),
            database.wordDao(),
            database.logDao(),
            database.quizHistoryDao()
        )

        val userId = userRepository.loggedInUserIdFlow.firstOrNull() ?: return Result.success()
        val user = userRepository.getLoggedInUser() ?: return Result.success()

        // Create notification channel
        NotificationHelper.createNotificationChannel(context)

        // Query due reviews
        val currentTimeMs = System.currentTimeMillis()
        val dueCount = database.progressDao().getDueWordsCount(userId, currentTimeMs).firstOrNull() ?: 0

        if (dueCount > 0) {
            NotificationHelper.showStudyReminderNotification(
                context = context,
                title = "Đã đến giờ ôn tập!",
                message = "Bạn có $dueCount từ vựng đã đến hạn ôn tập. Hãy mở MinLish ôn lại ngay nhé!"
            )
        } else {
            // General motivation reminder
            NotificationHelper.showStudyReminderNotification(
                context = context,
                title = "Học từ vựng mỗi ngày cùng MinLish",
                message = "Chào ${user.name}, hãy dành ra 5 phút hôm nay để tích lũy thêm từ mới và duy trì chuỗi Streak học tập nhé!"
            )
        }

        return Result.success()
    }

    companion object {
        private const val WORK_NAME = "minlish_daily_reminder_work"

        /**
         * Schedules a recurring periodic worker checking for due reviews every 12 hours.
         */
        fun schedulePeriodicWork(context: Context) {
            val workRequest = PeriodicWorkRequestBuilder<ReminderWorker>(
                12, TimeUnit.HOURS // Runs every 12 hours to check due lists
            )
            .setInitialDelay(1, TimeUnit.HOURS) // Delay initial run by 1 hour
            .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP, // Keep existing schedules
                workRequest
            )
        }
    }
}
