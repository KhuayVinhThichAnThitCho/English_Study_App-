package com.group16.study_english_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group16.study_english_app.data.local.entity.DeckEntity
import com.group16.study_english_app.data.local.entity.WordEntity
import com.group16.study_english_app.data.repository.VocabularyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

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
