package com.group16.study_english_app.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.group16.study_english_app.MainActivity
import com.group16.study_english_app.R

object NotificationHelper {
    
    private const val CHANNEL_ID = "minlish_study_reminders"
    private const val NOTIFICATION_ID = 1011

    /**
     * Registers a high-priority notification channel for Android 8.0+ devices.
     */
    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Nhắc nhở học tập MinLish"
            val descriptionText = "Nhắc ôn tập từ vựng đến hạn và lịch học hàng ngày"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * Builds and issues a local status notification reminding the user to study.
     */
    fun showStudyReminderNotification(context: Context, title: String, message: String) {
        // Create an intent to launch MainActivity when clicking the notification
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Using standard systems icons (Android system icon)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm) // Using standard built-in android alarm icon
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        try {
            val notificationManager = NotificationManagerCompat.from(context)
            // Checks for POST_NOTIFICATIONS permission on Android 13+ (done at runtime/handled by system, try-catch just in case)
            notificationManager.notify(NOTIFICATION_ID, builder.build())
        } catch (e: SecurityException) {
            // Gracefully ignore if permissions are not granted yet
        }
    }
}
