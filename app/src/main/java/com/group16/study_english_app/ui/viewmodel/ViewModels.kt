package com.group16.study_english_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.group16.study_english_app.data.local.entity.ActivityLogEntity
import com.group16.study_english_app.data.local.entity.DeckEntity
import com.group16.study_english_app.data.local.entity.QuizHistoryEntity
import com.group16.study_english_app.data.local.entity.UserEntity
import com.group16.study_english_app.data.local.entity.WordEntity
import com.group16.study_english_app.data.preferences.UserSessionDataStore
import com.group16.study_english_app.data.repository.LearningRepository
import com.group16.study_english_app.data.repository.UserRepository
import com.group16.study_english_app.data.repository.VocabularyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// ==========================================
// 1. AUTH VIEW MODEL
// ==========================================
class AuthViewModel(
    private val userRepository: UserRepository,
    private val sessionDataStore: UserSessionDataStore
) : ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.Loading)
    val userState: StateFlow<UserState> = _userState.asStateFlow()

    val isSrsDebugMode: StateFlow<Boolean> = sessionDataStore.srsDebugModeFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            val userId = userRepository.loggedInUserIdFlow.firstOrNull()
            if (userId != null) {
                val user = userRepository.getLoggedInUser()
                if (user != null) {
                    userRepository.updateStreak(user.id)
                    _userState.value = UserState.Authenticated(user)
                } else {
                    _userState.value = UserState.Unauthenticated
                }
            } else {
                _userState.value = UserState.Unauthenticated
            }
        }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            userRepository.login(email, password)
                .onSuccess { user ->
                    _userState.value = UserState.Authenticated(user)
                    onSuccess()
                }
                .onFailure { error ->
                    _userState.value = UserState.Error(error.message ?: "Đăng nhập thất bại")
                }
        }
    }

    fun loginWithGoogle(email: String, name: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            userRepository.loginOrRegisterWithGoogle(email, name)
                .onSuccess { user ->
                    _userState.value = UserState.Authenticated(user)
                    onSuccess()
                }
                .onFailure { error ->
                    _userState.value = UserState.Error(error.message ?: "Đăng nhập Google thất bại")
                }
        }
    }

    fun register(
        email: String,
        password: String,
        name: String,
        targetGoal: String,
        level: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            userRepository.register(email, password, name, targetGoal, level)
                .onSuccess {
                    // Auto login after registration
                    login(email, password, onSuccess)
                }
                .onFailure { error ->
                    _userState.value = UserState.Error(error.message ?: "Đăng ký thất bại")
                }
        }
    }

    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            userRepository.logout()
            _userState.value = UserState.Unauthenticated
            onSuccess()
        }
    }

    fun updateUserProfile(name: String, targetGoal: String, level: String, dailyWordLimit: Int) {
        val currentState = _userState.value
        if (currentState is UserState.Authenticated) {
            viewModelScope.launch {
                userRepository.updateUserProfile(currentState.user.id, name, targetGoal, level, dailyWordLimit)
                val updatedUser = userRepository.getLoggedInUser()
                if (updatedUser != null) {
                    _userState.value = UserState.Authenticated(updatedUser)
                }
            }
        }
    }

    fun toggleSrsDebugMode(enabled: Boolean) {
        viewModelScope.launch {
            sessionDataStore.setSrsDebugMode(enabled)
        }
    }

    fun clearError() {
        if (_userState.value is UserState.Error) {
            _userState.value = UserState.Unauthenticated
        }
    }
}

sealed interface UserState {
    object Loading : UserState
    object Unauthenticated : UserState
    data class Authenticated(val user: UserEntity) : UserState
    data class Error(val message: String) : UserState
}

// ==========================================
// 2. VOCABULARY VIEW MODEL
// ==========================================
class VocabularyViewModel(
    private val vocabularyRepository: VocabularyRepository
) : ViewModel() {

    private val _userId = MutableStateFlow<Long?>(null)
    val userId: StateFlow<Long?> = _userId.asStateFlow()

    val decks: StateFlow<List<DeckEntity>> = _userId
        .flatMapLatest { id ->
            if (id != null) vocabularyRepository.getDecksForUser(id) else flowOf(emptyList())
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _currentDeck = MutableStateFlow<DeckEntity?>(null)
    val currentDeck: StateFlow<DeckEntity?> = _currentDeck.asStateFlow()

    private val _words = MutableStateFlow<List<WordEntity>>(emptyList())
    val words: StateFlow<List<WordEntity>> = _words.asStateFlow()

    private val _csvImportResult = MutableStateFlow<ResultState<Int>>(ResultState.Idle)
    val csvImportResult: StateFlow<ResultState<Int>> = _csvImportResult.asStateFlow()

    private val _csvExportResult = MutableStateFlow<ResultState<String>>(ResultState.Idle)
    val csvExportResult: StateFlow<ResultState<String>> = _csvExportResult.asStateFlow()

    fun setUserId(userId: Long) {
        _userId.value = userId
    }

    fun loadDeckDetails(deckId: Long) {
        viewModelScope.launch {
            val deck = vocabularyRepository.getDeckById(deckId)
            _currentDeck.value = deck
            if (deck != null) {
                vocabularyRepository.getWordsForDeck(deckId).collect {
                    _words.value = it
                }
            }
        }
    }

    fun createDeck(userId: Long, name: String, description: String, tags: String) {
        viewModelScope.launch {
            vocabularyRepository.createDeck(userId, name, description, tags)
        }
    }

    fun updateDeck(deck: DeckEntity) {
        viewModelScope.launch {
            vocabularyRepository.updateDeck(deck)
        }
    }

    fun deleteDeck(deck: DeckEntity) {
        viewModelScope.launch {
            vocabularyRepository.deleteDeck(deck)
        }
    }

    fun addWord(deckId: Long, word: String, pronunciation: String, meaning: String, descriptionEn: String, example: String, collocation: String, relatedWords: String, note: String) {
        viewModelScope.launch {
            val wordEntity = WordEntity(
                deckId = deckId,
                word = word,
                pronunciation = pronunciation,
                meaning = meaning,
                descriptionEn = descriptionEn,
                example = example,
                collocation = collocation,
                relatedWords = relatedWords,
                note = note
            )
            vocabularyRepository.addWord(wordEntity)
            loadDeckDetails(deckId)
        }
    }

    fun updateWord(word: WordEntity) {
        viewModelScope.launch {
            vocabularyRepository.updateWord(word)
            loadDeckDetails(word.deckId)
        }
    }

    fun deleteWord(word: WordEntity) {
        viewModelScope.launch {
            vocabularyRepository.deleteWord(word)
            loadDeckDetails(word.deckId)
        }
    }

    fun importCSV(csvContent: String, deckId: Long) {
        viewModelScope.launch {
            _csvImportResult.value = ResultState.Loading
            vocabularyRepository.importFromCSV(csvContent, deckId)
                .onSuccess { count ->
                    _csvImportResult.value = ResultState.Success(count)
                    loadDeckDetails(deckId)
                }
                .onFailure { error ->
                    _csvImportResult.value = ResultState.Error(error.message ?: "Lỗi khi import CSV")
                }
        }
    }

    fun exportCSV(deckId: Long) {
        viewModelScope.launch {
            _csvExportResult.value = ResultState.Loading
            vocabularyRepository.exportToCSV(deckId)
                .onSuccess { csvString ->
                    _csvExportResult.value = ResultState.Success(csvString)
                }
                .onFailure { error ->
                    _csvExportResult.value = ResultState.Error(error.message ?: "Lỗi khi export CSV")
                }
        }
    }

    fun resetCsvStates() {
        _csvImportResult.value = ResultState.Idle
        _csvExportResult.value = ResultState.Idle
    }
}

sealed interface ResultState<out T> {
    object Idle : ResultState<Nothing>
    object Loading : ResultState<Nothing>
    data class Success<T>(val data: T) : ResultState<T>
    data class Error(val message: String) : ResultState<Nothing>
}

// ==========================================
// 3. LEARNING VIEW MODEL
// ==========================================
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

// ==========================================
// 4. PRACTICE VIEW MODEL
// ==========================================
class PracticeViewModel(
    private val vocabularyRepository: VocabularyRepository,
    private val learningRepository: LearningRepository
) : ViewModel() {

    private val _quizQuestions = MutableStateFlow<List<QuizQuestion>>(emptyList())
    val quizQuestions: StateFlow<List<QuizQuestion>> = _quizQuestions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _selectedAnswerIndex = MutableStateFlow<Int?>(null)
    val selectedAnswerIndex: StateFlow<Int?> = _selectedAnswerIndex.asStateFlow()

    private val _typedAnswer = MutableStateFlow("")
    val typedAnswer: StateFlow<String> = _typedAnswer.asStateFlow()

    private val _correctCount = MutableStateFlow(0)
    val correctCount: StateFlow<Int> = _correctCount.asStateFlow()

    private val _isQuizFinished = MutableStateFlow(false)
    val isQuizFinished: StateFlow<Boolean> = _isQuizFinished.asStateFlow()

    private val _quizHistory = MutableStateFlow<List<QuizHistoryEntity>>(emptyList())
    val quizHistory: StateFlow<List<QuizHistoryEntity>> = _quizHistory.asStateFlow()

    private var lastLoadedUserId: Long? = null

    fun loadQuizHistory(userId: Long) {
        if (lastLoadedUserId == userId) return
        lastLoadedUserId = userId
        viewModelScope.launch {
            learningRepository.getQuizHistory(userId).collect {
                _quizHistory.value = it
            }
        }
    }

    fun startQuiz(deckId: Long) {
        viewModelScope.launch {
            _currentQuestionIndex.value = 0
            _selectedAnswerIndex.value = null
            _typedAnswer.value = ""
            _correctCount.value = 0
            _isQuizFinished.value = false

            val wordsInDeck = vocabularyRepository.getWordsForDeck(deckId).firstOrNull() ?: emptyList()
            if (wordsInDeck.size < 2) {
                _quizQuestions.value = emptyList()
                return@launch
            }

            val questions = mutableListOf<QuizQuestion>()
            // Generate up to 10 questions
            val activeWords = wordsInDeck.shuffled().take(10)
            for (word in activeWords) {
                val quizType = if (Math.random() > 0.5) QuizType.MULTIPLE_CHOICE else QuizType.TYPING
                
                if (quizType == QuizType.MULTIPLE_CHOICE) {
                    val meaning = word.meaning
                    val decoys = wordsInDeck
                        .filter { it.id != word.id }
                        .map { it.meaning }
                        .distinct()
                        .shuffled()
                        .take(3)
                    
                    val options = (decoys + meaning).shuffled()
                    val correctIdx = options.indexOf(meaning)

                    questions.add(
                        QuizQuestion(
                            word = word,
                            type = QuizType.MULTIPLE_CHOICE,
                            questionText = "Nghĩa của từ '${word.word}' là gì?",
                            options = options,
                            correctOptionIndex = correctIdx
                        )
                    )
                } else {
                    questions.add(
                        QuizQuestion(
                            word = word,
                            type = QuizType.TYPING,
                            questionText = "Gõ từ tương ứng với nghĩa: '${word.meaning}'\nGợi ý phiên âm: ${word.pronunciation}",
                            options = emptyList(),
                            correctOptionIndex = -1
                        )
                    )
                }
            }
            _quizQuestions.value = questions
        }
    }

    fun submitMultipleChoice(optionIndex: Int, userId: Long) {
        val currentQ = _quizQuestions.value.getOrNull(_currentQuestionIndex.value) ?: return
        _selectedAnswerIndex.value = optionIndex
        if (optionIndex == currentQ.correctOptionIndex) {
            _correctCount.value = _correctCount.value + 1
        }
        
        viewModelScope.launch {
            // Log as a reviewed activity log
            learningRepository.updateSrsProgress(userId, currentQ.word.id, if (optionIndex == currentQ.correctOptionIndex) 4 else 1, false)
        }
    }

    fun submitTyping(answer: String, userId: Long) {
        val currentQ = _quizQuestions.value.getOrNull(_currentQuestionIndex.value) ?: return
        _typedAnswer.value = answer
        val isCorrect = answer.trim().equals(currentQ.word.word.trim(), ignoreCase = true)
        if (isCorrect) {
            _correctCount.value = _correctCount.value + 1
        }

        viewModelScope.launch {
            // Log as reviewed
            learningRepository.updateSrsProgress(userId, currentQ.word.id, if (isCorrect) 4 else 1, false)
        }
    }

    fun nextQuestion(userId: Long) {
        _selectedAnswerIndex.value = null
        _typedAnswer.value = ""
        val nextIdx = _currentQuestionIndex.value + 1
        if (nextIdx < _quizQuestions.value.size) {
            _currentQuestionIndex.value = nextIdx
        } else {
            _isQuizFinished.value = true
            // Save quiz history
            viewModelScope.launch {
                learningRepository.insertQuizHistory(userId, _correctCount.value, _quizQuestions.value.size)
                loadQuizHistory(userId)
            }
        }
    }
}

enum class QuizType {
    MULTIPLE_CHOICE, TYPING
}

data class QuizQuestion(
    val word: WordEntity,
    val type: QuizType,
    val questionText: String,
    val options: List<String>,
    val correctOptionIndex: Int
)

// ==========================================
// 5. DASHBOARD VIEW MODEL
// ==========================================
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
            learningRepository.getRecentActivityLogs(userId, 7).collect { logs ->
                _weeklyActivityLogs.value = fillMissingDays(logs, userId)
            }
        }
    }

    private fun fillMissingDays(logs: List<ActivityLogEntity>, userId: Long): List<ActivityLogEntity> {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val cal = Calendar.getInstance()
        val result = mutableListOf<ActivityLogEntity>()

        // Generate 7 days back
        for (i in 0..6) {
            val dateStr = sdf.format(cal.time)
            val matchingLog = logs.find { it.dateString == dateStr }
            if (matchingLog != null) {
                result.add(matchingLog)
            } else {
                result.add(ActivityLogEntity(userId = userId, dateString = dateStr, actionType = "LEARNED", count = 0))
            }
            cal.add(Calendar.DATE, -1)
        }
        return result.reversed() // Oldest first (for chart rendering)
    }
}

// ==========================================
// VIEW MODEL FACTORY
// ==========================================
class ViewModelFactory(
    private val userRepository: UserRepository,
    private val vocabularyRepository: VocabularyRepository,
    private val learningRepository: LearningRepository,
    private val sessionDataStore: UserSessionDataStore
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(userRepository, sessionDataStore) as T
            }
            modelClass.isAssignableFrom(VocabularyViewModel::class.java) -> {
                VocabularyViewModel(vocabularyRepository) as T
            }
            modelClass.isAssignableFrom(LearningViewModel::class.java) -> {
                LearningViewModel(learningRepository, sessionDataStore) as T
            }
            modelClass.isAssignableFrom(PracticeViewModel::class.java) -> {
                PracticeViewModel(vocabularyRepository, learningRepository) as T
            }
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                DashboardViewModel(learningRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
