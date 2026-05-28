package com.group16.study_english_app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.group16.study_english_app.data.local.AppDatabase
import com.group16.study_english_app.data.preferences.UserSessionDataStore
import com.group16.study_english_app.data.repository.LearningRepository
import com.group16.study_english_app.data.repository.UserRepository
import com.group16.study_english_app.data.repository.VocabularyRepository
import com.group16.study_english_app.ui.navigation.AppNavGraph
import com.group16.study_english_app.ui.navigation.Screen
import com.group16.study_english_app.ui.theme.Study_English_AppTheme
import com.group16.study_english_app.ui.viewmodel.AuthViewModel
import com.group16.study_english_app.ui.viewmodel.DashboardViewModel
import com.group16.study_english_app.ui.viewmodel.LearningViewModel
import com.group16.study_english_app.ui.viewmodel.PracticeViewModel
import com.group16.study_english_app.ui.viewmodel.UserState
import com.group16.study_english_app.ui.viewmodel.ViewModelFactory
import com.group16.study_english_app.ui.viewmodel.VocabularyViewModel
import com.group16.study_english_app.worker.NotificationHelper
import com.group16.study_english_app.worker.ReminderWorker

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // 1. Initialize Local Storage and Repositories
        val database = AppDatabase.getDatabase(applicationContext)
        val sessionDataStore = UserSessionDataStore(applicationContext)
        val vocabularyRepository = VocabularyRepository(
            database.deckDao(),
            database.wordDao(),
            database.progressDao()
        )
        val userRepository = UserRepository(
            database.userDao(),
            sessionDataStore,
            vocabularyRepository
        )
        val learningRepository = LearningRepository(
            database.progressDao(),
            database.wordDao(),
            database.logDao(),
            database.quizHistoryDao()
        )

        // ViewModel Factory
        val factory = ViewModelFactory(
            userRepository,
            vocabularyRepository,
            learningRepository,
            sessionDataStore
        )

        // 2. Request Notification Permissions on Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permissionCheck = checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
            if (permissionCheck != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 101)
            }
        }

        // 3. Setup Channels & Background WorkManager schedules
        NotificationHelper.createNotificationChannel(applicationContext)
        ReminderWorker.schedulePeriodicWork(applicationContext)

        setContent {
            Study_English_AppTheme {
                val authViewModel: AuthViewModel = viewModel(factory = factory)
                val vocabularyViewModel: VocabularyViewModel = viewModel(factory = factory)
                val learningViewModel: LearningViewModel = viewModel(factory = factory)
                val practiceViewModel: PracticeViewModel = viewModel(factory = factory)
                val dashboardViewModel: DashboardViewModel = viewModel(factory = factory)

                val userState by authViewModel.userState.collectAsState()

                when (userState) {
                    is UserState.Loading -> {
                        // Beautiful loading splash state
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    else -> {
                        val navController = rememberNavController()
                        // Decides start screen route based on login session status
                        val startDestination = if (userState is UserState.Authenticated) {
                            Screen.Dashboard.route
                        } else {
                            Screen.Login.route
                        }

                        AppNavGraph(
                            navController = navController,
                            authViewModel = authViewModel,
                            vocabularyViewModel = vocabularyViewModel,
                            learningViewModel = learningViewModel,
                            practiceViewModel = practiceViewModel,
                            dashboardViewModel = dashboardViewModel,
                            startDestination = startDestination
                        )
                    }
                }
            }
        }
    }
}