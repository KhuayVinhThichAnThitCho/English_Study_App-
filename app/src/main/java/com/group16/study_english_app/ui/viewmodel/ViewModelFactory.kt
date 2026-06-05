package com.group16.study_english_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.group16.study_english_app.data.preferences.UserSessionDataStore
import com.group16.study_english_app.data.repository.LearningRepository
import com.group16.study_english_app.data.repository.UserRepository
import com.group16.study_english_app.data.repository.VocabularyRepository

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
