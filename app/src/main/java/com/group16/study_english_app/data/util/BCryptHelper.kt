package com.group16.study_english_app.data.util

import org.mindrot.jbcrypt.BCrypt

object BCryptHelper {
    
    /**
     * Hashes a plain-text password using BCrypt.
     */
    fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt(10))
    }

    /**
     * Checks if a plain-text password matches a BCrypt hash.
     */
    fun checkPassword(password: String, hashed: String): Boolean {
        return try {
            BCrypt.checkpw(password, hashed)
        } catch (e: Exception) {
            false
        }
    }
}
