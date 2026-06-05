package com.group16.study_english_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group16.study_english_app.data.local.entity.WordEntity
import com.group16.study_english_app.data.preferences.UserSessionDataStore
import com.group16.study_english_app.data.repository.LearningRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class LearningViewModel(
    private val learningRepository: LearningRepository,
    private val sessionDataStore: UserSessionDataStore
) : ViewModel() {

    private val _learningQueue = MutableStateFlow<List<WordEntity>>(emptyList())
    val learningQueue: StateFlow<List<WordEntity>> = _learningQueue.asStateFlow()

    private val _currentIndex = MutableStateFlow(0)
    val currentIndex: StateFlow<Int> = _currentIndex.asStateFlow()

    private val _isFlipped = MutableStateFlow(false)
    val isFlipped: StateFlow<Boolean> = _isFlipped.asStateFlow()

    private val _isFinished = MutableStateFlow(false)
    val isFinished: StateFlow<Boolean> = _isFinished.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadDailySession(userId: Long, dailyWordLimit: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _isFinished.value = false
            _currentIndex.value = 0
            _isFlipped.value = false

            val newWords = learningRepository.getNewWords(userId, dailyWordLimit)
            val dueWords = learningRepository.getDueWords(userId, System.currentTimeMillis(), 30) // cap due review to 30

            // Combine them: new words first or due words first? Usually mix them
            val queue = (dueWords + newWords).shuffled()
            _learningQueue.value = queue
            _isLoading.value = false
            if (queue.isEmpty()) {
                _isFinished.value = true
            }
        }
    }

    fun flipCard() {
        _isFlipped.value = !_isFlipped.value
    }

    /**
     * Submit user feedback: 1 = Again, 3 = Hard, 4 = Good, 5 = Easy
     */
    fun submitFeedback(userId: Long, wordId: Long, feedback: Int) {
        viewModelScope.launch {
            val isDebugMinutes = sessionDataStore.srsDebugModeFlow.firstOrNull() ?: false
            learningRepository.updateSrsProgress(userId, wordId, feedback, isDebugMinutes)
            
            // Go to next card
            _isFlipped.value = false
            val nextIdx = _currentIndex.value + 1
            if (nextIdx < _learningQueue.value.size) {
                _currentIndex.value = nextIdx
            } else {
                _isFinished.value = true
            }
        }
    }
}
