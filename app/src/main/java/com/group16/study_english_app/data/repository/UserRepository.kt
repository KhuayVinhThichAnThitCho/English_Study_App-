package com.group16.study_english_app.data.repository

import com.group16.study_english_app.data.local.dao.UserDao
import com.group16.study_english_app.data.local.entity.UserEntity
import com.group16.study_english_app.data.preferences.UserSessionDataStore
import com.group16.study_english_app.data.util.BCryptHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class UserRepository(
    private val userDao: UserDao,
    private val sessionDataStore: UserSessionDataStore,
    private val vocabularyRepository: VocabularyRepository
) {
    val loggedInUserIdFlow: Flow<Long?> = sessionDataStore.userIdFlow

    suspend fun getLoggedInUser(): UserEntity? {
        val userId = sessionDataStore.userIdFlow.firstOrNull() ?: return null
        return userDao.getUserById(userId)
    }

    fun getLoggedInUserFlow(userId: Long): Flow<UserEntity?> {
        return userDao.getUserByIdFlow(userId)
    }

    suspend fun register(
        email: String,
        password: String,
        name: String,
        targetGoal: String,
        level: String
    ): Result<UserEntity> {
        val existingUser = userDao.getUserByEmail(email)
        if (existingUser != null) {
            return Result.failure(Exception("Email đã được đăng ký sử dụng"))
        }
        val passwordHash = BCryptHelper.hashPassword(password)
        val user = UserEntity(
            email = email,
            passwordHash = passwordHash,
            name = name,
            targetGoal = targetGoal,
            level = level,
            lastActiveDate = getTodayString()
        )
        val id = userDao.insertUser(user)
        // Seed sample data for user
        vocabularyRepository.prepopulateSampleData(id)
        
        val registeredUser = user.copy(id = id)
        return Result.success(registeredUser)
    }

    suspend fun login(email: String, password: String): Result<UserEntity> {
        val user = userDao.getUserByEmail(email) ?: return Result.failure(Exception("Email không tồn tại"))
        if (!BCryptHelper.checkPassword(password, user.passwordHash)) {
            return Result.failure(Exception("Mật khẩu không chính xác"))
        }
        
        // Generate mock JWT token
        val mockToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." + UUID.randomUUID().toString()
        val updatedUser = user.copy(mockToken = mockToken)
        userDao.updateUser(updatedUser)
        
        // Save session in Preferences DataStore
        sessionDataStore.saveSession(updatedUser.id, mockToken)
        
        // Update user activity streak
        updateStreak(updatedUser.id)
        
        return Result.success(updatedUser)
    }

    suspend fun logout() {
        sessionDataStore.clearSession()
    }

    suspend fun loginOrRegisterWithGoogle(email: String, name: String): Result<UserEntity> {
        val existingUser = userDao.getUserByEmail(email)
        val user = if (existingUser != null) {
            existingUser
        } else {
            val newUser = UserEntity(
                email = email,
                passwordHash = "", // Mật khẩu trống cho Google SSO
                name = name,
                targetGoal = "Giao tiếp",
                level = "A1",
                lastActiveDate = getTodayString()
            )
            val id = userDao.insertUser(newUser)
            vocabularyRepository.prepopulateSampleData(id)
            newUser.copy(id = id)
        }
        
        val mockToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.GOOGLE." + java.util.UUID.randomUUID().toString()
        val updatedUser = user.copy(mockToken = mockToken)
        userDao.updateUser(updatedUser)
        
        sessionDataStore.saveSession(updatedUser.id, mockToken)
        updateStreak(updatedUser.id)
        
        return Result.success(updatedUser)
    }

    suspend fun updateUserProfile(userId: Long, name: String, targetGoal: String, level: String, dailyWordLimit: Int) {
        val user = userDao.getUserById(userId) ?: return
        val updatedUser = user.copy(
            name = name,
            targetGoal = targetGoal,
            level = level,
            dailyWordLimit = dailyWordLimit
        )
        userDao.updateUser(updatedUser)
    }

    suspend fun updateStreak(userId: Long) {
        val user = userDao.getUserById(userId) ?: return
        val today = getTodayString()
        val yesterday = getYesterdayString()
        
        val lastActive = user.lastActiveDate
        var streak = user.streakCount
        
        if (lastActive == today) {
            return
        }
        
        if (lastActive == yesterday) {
            streak += 1
        } else {
            streak = 1
        }
        
        val updatedUser = user.copy(
            streakCount = streak,
            lastActiveDate = today
        )
        userDao.updateUser(updatedUser)
    }

    private fun getTodayString(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }

    private fun getYesterdayString(): String {
        val cal = java.util.Calendar.getInstance()
        cal.add(java.util.Calendar.DATE, -1)
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cal.time)
    }
}
