package com.group16.study_english_app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [Index(value = ["email"], unique = true)]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val email: String,
    val passwordHash: String,
    val name: String,
    val targetGoal: String, // e.g., "IELTS", "TOEIC", "Giao tiếp", "Khác"
    val level: String, // e.g., "A1", "A2", "B1", "B2", "C1", "C2"
    val streakCount: Int = 0,
    val lastActiveDate: String = "", // e.g., "YYYY-MM-DD"
    val dailyWordLimit: Int = 10,
    val mockToken: String = ""
)

@Entity(
    tableName = "decks",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userId"])]
)
data class DeckEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val name: String,
    val description: String,
    val tags: String, // Comma separated tags: e.g. "IELTS, Travel, Business"
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "words",
    foreignKeys = [
        ForeignKey(
            entity = DeckEntity::class,
            parentColumns = ["id"],
            childColumns = ["deckId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["deckId"])]
)
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val deckId: Long,
    val word: String,
    val pronunciation: String,
    val meaning: String,
    val descriptionEn: String,
    val example: String,
    val collocation: String,
    val relatedWords: String, // Synonyms / Antonyms
    val note: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "learning_progress",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = WordEntity::class,
            parentColumns = ["id"],
            childColumns = ["wordId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["userId"]),
        Index(value = ["wordId"], unique = true) // One progress per word per user
    ]
)
data class ProgressEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val wordId: Long,
    val easeFactor: Double = 2.5,
    val repetitions: Int = 0,
    val intervalDays: Int = 0,
    val nextReviewTimeMs: Long = System.currentTimeMillis(),
    val lastReviewedTimeMs: Long = 0,
    val status: String = "NEW" // "NEW", "LEARNING", "REVIEW", "MASTERED"
)

@Entity(
    tableName = "activity_logs",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userId"])]
)
data class ActivityLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val dateString: String, // e.g., "YYYY-MM-DD"
    val actionType: String, // "LEARNED", "REVIEWED"
    val count: Int
)

@Entity(
    tableName = "quiz_history",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userId"])]
)
data class QuizHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val dateString: String, // e.g., "YYYY-MM-DD"
    val correctCount: Int,
    val totalCount: Int
)
