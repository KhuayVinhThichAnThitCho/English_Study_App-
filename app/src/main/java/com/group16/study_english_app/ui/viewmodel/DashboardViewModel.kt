package com.group16.study_english_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group16.study_english_app.data.local.entity.ActivityLogEntity
import com.group16.study_english_app.data.repository.LearningRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DashboardViewModel(
    private val learningRepository: LearningRepository
) : ViewModel() {

    private val _userId = MutableStateFlow<Long?>(null)

    val learnedCount: StateFlow<Int> = _userId
        .flatMapLatest { id ->
            if (id != null) learningRepository.getLearnedWordsCount(id) else flowOf(0)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val dueCount: StateFlow<Int> = _userId
        .flatMapLatest { id ->
            if (id != null) learningRepository.getDueWordsCount(id, System.currentTimeMillis()) else flowOf(0)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    // Activity Logs for past 7 days
    private val _weeklyActivityLogs = MutableStateFlow<List<ActivityLogEntity>>(emptyList())
    val weeklyActivityLogs: StateFlow<List<ActivityLogEntity>> = _weeklyActivityLogs.asStateFlow()

    // Level estimation flow
    val userLevel: StateFlow<String> = learnedCount
        .combine(dueCount) { learned, due ->
            when {
                learned < 20 -> "Beginner"
                learned in 20..100 -> "Intermediate"
                else -> "Advanced"
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "Beginner")

    fun setUserId(userId: Long) {
        if (_userId.value == userId) return
        _userId.value = userId
        loadWeeklyActivityLogs(userId)
    }

    private fun loadWeeklyActivityLogs(userId: Long) {
        viewModelScope.launch {
            // Tính ngày cách đây 6 ngày (để lấy đủ 7 ngày bao gồm hôm nay)
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE, -6)
            val sinceDate = sdf.format(cal.time)

            learningRepository.getActivityLogsSinceDate(userId, sinceDate).collect { logs ->
                _weeklyActivityLogs.value = fillMissingDays(logs, userId)
            }
        }
    }

    private fun fillMissingDays(logs: List<ActivityLogEntity>, userId: Long): List<ActivityLogEntity> {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val cal = Calendar.getInstance()
        val result = mutableListOf<ActivityLogEntity>()

        // Tạo map tổng hợp count theo ngày (cộng dồn LEARNED + REVIEWED)
        val countByDate = mutableMapOf<String, Int>()
        for (log in logs) {
            countByDate[log.dateString] = (countByDate[log.dateString] ?: 0) + log.count
        }

        // Luôn sinh ra 7 ngày liên tiếp tính từ hôm nay trở về trước
        for (i in 0..6) {
            val dateStr = sdf.format(cal.time)
            val totalCount = countByDate[dateStr] ?: 0
            result.add(ActivityLogEntity(userId = userId, dateString = dateStr, actionType = "LEARNED", count = totalCount))
            cal.add(Calendar.DATE, -1)
        }
        return result.reversed()
    }
}
