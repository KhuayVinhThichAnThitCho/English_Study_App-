package com.group16.study_english_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.group16.study_english_app.ui.screens.auth.LoginScreen
import com.group16.study_english_app.ui.screens.auth.RegisterScreen
import com.group16.study_english_app.ui.screens.auth.ProfileSetupScreen
import com.group16.study_english_app.ui.screens.dashboard.DashboardScreen
import com.group16.study_english_app.ui.screens.vocab.DecksScreen
import com.group16.study_english_app.ui.screens.vocab.DeckDetailScreen
import com.group16.study_english_app.ui.screens.vocab.AddWordScreen
import com.group16.study_english_app.ui.screens.learning.LearningScreen
import com.group16.study_english_app.ui.screens.practice.PracticeScreen
import com.group16.study_english_app.ui.screens.practice.QuizScreen
import com.group16.study_english_app.ui.screens.settings.SettingsScreen
import com.group16.study_english_app.ui.viewmodel.AuthViewModel
import com.group16.study_english_app.ui.viewmodel.VocabularyViewModel
import com.group16.study_english_app.ui.viewmodel.LearningViewModel
import com.group16.study_english_app.ui.viewmodel.PracticeViewModel
import com.group16.study_english_app.ui.viewmodel.DashboardViewModel
import com.group16.study_english_app.ui.viewmodel.UserState

@Composable
fun AppNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    vocabularyViewModel: VocabularyViewModel,
    learningViewModel: LearningViewModel,
    practiceViewModel: PracticeViewModel,
    dashboardViewModel: DashboardViewModel,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = authViewModel,
                onNavigateToRegister = { navController.navigate(Screen.Register.route) },
                onLoginSuccess = { 
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    } 
                }
            )
        }
        
        composable(Screen.Register.route) {
            RegisterScreen(
                viewModel = authViewModel,
                onNavigateToLogin = { navController.navigate(Screen.Login.route) },
                onRegisterSuccess = { 
                    navController.navigate(Screen.ProfileSetup.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    } 
                }
            )
        }
        
        composable(Screen.ProfileSetup.route) {
            ProfileSetupScreen(
                viewModel = authViewModel,
                onSetupComplete = { 
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.ProfileSetup.route) { inclusive = true }
                    } 
                }
            )
        }
        
        composable(Screen.Dashboard.route) {
            val userState by authViewModel.userState.collectAsState()
            val user = (userState as? UserState.Authenticated)?.user
            if (user != null) {
                LaunchedEffect(user.id) {
                    dashboardViewModel.setUserId(user.id)
                    vocabularyViewModel.setUserId(user.id)
                }
            }
            
            DashboardScreen(
                authViewModel = authViewModel,
                dashboardViewModel = dashboardViewModel,
                onNavigateToDecks = { navController.navigate(Screen.Decks.route) },
                onNavigateToLearning = { navController.navigate(Screen.Learning.route) },
                onNavigateToPractice = { navController.navigate(Screen.Practice.route) },
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) }
            )
        }
        
        composable(Screen.Decks.route) {
            val userState by authViewModel.userState.collectAsState()
            val user = (userState as? UserState.Authenticated)?.user
            if (user != null) {
                LaunchedEffect(user.id) {
                    vocabularyViewModel.setUserId(user.id)
                }
            }
            DecksScreen(
                viewModel = vocabularyViewModel,
                onNavigateToDetail = { deckId -> navController.navigate(Screen.DeckDetail.createRoute(deckId)) },
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(
            route = Screen.DeckDetail.route,
            arguments = listOf(navArgument("deckId") { type = NavType.LongType })
        ) { backStackEntry ->
            val deckId = backStackEntry.arguments?.getLong("deckId") ?: 0L
            val userState by authViewModel.userState.collectAsState()
            val user = (userState as? UserState.Authenticated)?.user
            
            DeckDetailScreen(
                deckId = deckId,
                viewModel = vocabularyViewModel,
                userEntity = user,
                onNavigateToAddWord = { navController.navigate(Screen.AddWord.createRoute(deckId)) },
                onNavigateToQuiz = { navController.navigate(Screen.Quiz.createRoute(deckId)) },
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(
            route = Screen.AddWord.route,
            arguments = listOf(navArgument("deckId") { type = NavType.LongType })
        ) { backStackEntry ->
            val deckId = backStackEntry.arguments?.getLong("deckId") ?: 0L
            AddWordScreen(
                deckId = deckId,
                viewModel = vocabularyViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Learning.route) {
            val userState by authViewModel.userState.collectAsState()
            val user = (userState as? UserState.Authenticated)?.user
            if (user != null) {
                LaunchedEffect(user.id) {
                    learningViewModel.loadDailySession(user.id, user.dailyWordLimit)
                }
            }
            LearningScreen(
                learningViewModel = learningViewModel,
                userEntity = user,
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Practice.route) {
            val userState by authViewModel.userState.collectAsState()
            val user = (userState as? UserState.Authenticated)?.user
            if (user != null) {
                LaunchedEffect(user.id) {
                    vocabularyViewModel.setUserId(user.id)
                    practiceViewModel.loadQuizHistory(user.id)
                }
            }
            PracticeScreen(
                vocabularyViewModel = vocabularyViewModel,
                practiceViewModel = practiceViewModel,
                onNavigateToQuiz = { deckId -> navController.navigate(Screen.Quiz.createRoute(deckId)) },
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(
            route = Screen.Quiz.route,
            arguments = listOf(navArgument("deckId") { type = NavType.LongType })
        ) { backStackEntry ->
            val deckId = backStackEntry.arguments?.getLong("deckId") ?: 0L
            val userState = authViewModel.userState.value
            val user = if (userState is UserState.Authenticated) userState.user else null
            
            QuizScreen(
                deckId = deckId,
                practiceViewModel = practiceViewModel,
                userEntity = user,
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Settings.route) {
            val userState by authViewModel.userState.collectAsState()
            val user = (userState as? UserState.Authenticated)?.user
            SettingsScreen(
                authViewModel = authViewModel,
                userEntity = user,
                onLogout = {
                    authViewModel.logout {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
