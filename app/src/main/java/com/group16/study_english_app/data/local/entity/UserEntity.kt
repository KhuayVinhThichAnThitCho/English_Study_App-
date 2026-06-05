package com.group16.study_english_app.data.local.entity

import androidx.room.Entity
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
