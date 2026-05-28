package com.group16.study_english_app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.group16.study_english_app.data.local.dao.DeckDao
import com.group16.study_english_app.data.local.dao.LogDao
import com.group16.study_english_app.data.local.dao.ProgressDao
import com.group16.study_english_app.data.local.dao.QuizHistoryDao
import com.group16.study_english_app.data.local.dao.UserDao
import com.group16.study_english_app.data.local.dao.WordDao
import com.group16.study_english_app.data.local.entity.ActivityLogEntity
import com.group16.study_english_app.data.local.entity.DeckEntity
import com.group16.study_english_app.data.local.entity.ProgressEntity
import com.group16.study_english_app.data.local.entity.QuizHistoryEntity
import com.group16.study_english_app.data.local.entity.UserEntity
import com.group16.study_english_app.data.local.entity.WordEntity

@Database(
    entities = [
        UserEntity::class,
        DeckEntity::class,
        WordEntity::class,
        ProgressEntity::class,
        ActivityLogEntity::class,
        QuizHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun deckDao(): DeckDao
    abstract fun wordDao(): WordDao
    abstract fun progressDao(): ProgressDao
    abstract fun logDao(): LogDao
    abstract fun quizHistoryDao(): QuizHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "minlish_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
