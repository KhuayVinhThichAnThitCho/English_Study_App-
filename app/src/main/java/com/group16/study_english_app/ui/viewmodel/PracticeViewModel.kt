package com.group16.study_english_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group16.study_english_app.data.local.entity.QuizHistoryEntity
import com.group16.study_english_app.data.local.entity.WordEntity
import com.group16.study_english_app.data.repository.LearningRepository
import com.group16.study_english_app.data.repository.VocabularyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

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
