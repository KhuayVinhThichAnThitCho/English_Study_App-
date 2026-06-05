package com.group16.study_english_app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

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
