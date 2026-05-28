package com.group16.study_english_app.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object ProfileSetup : Screen("profile_setup")
    object Dashboard : Screen("dashboard")
    object Decks : Screen("decks")
    
    object DeckDetail : Screen("deck_detail/{deckId}") {
        fun createRoute(deckId: Long) = "deck_detail/$deckId"
    }
    
    object AddWord : Screen("add_word/{deckId}") {
        fun createRoute(deckId: Long) = "add_word/$deckId"
    }
    
    object Learning : Screen("learning")
    object Practice : Screen("practice")
    
    object Quiz : Screen("quiz/{deckId}") {
        fun createRoute(deckId: Long) = "quiz/$deckId"
    }
    
    object Settings : Screen("settings")
}
