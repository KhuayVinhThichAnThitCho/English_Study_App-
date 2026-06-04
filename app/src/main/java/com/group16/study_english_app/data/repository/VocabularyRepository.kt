package com.group16.study_english_app.data.repository

import com.group16.study_english_app.data.local.dao.DeckDao
import com.group16.study_english_app.data.local.dao.ProgressDao
import com.group16.study_english_app.data.local.dao.WordDao
import com.group16.study_english_app.data.local.entity.DeckEntity
import com.group16.study_english_app.data.local.entity.ProgressEntity
import com.group16.study_english_app.data.local.entity.WordEntity
import com.group16.study_english_app.data.util.CSVHelper
import com.group16.study_english_app.data.util.SampleData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class VocabularyRepository(
    private val deckDao: DeckDao,
    private val wordDao: WordDao,
    private val progressDao: ProgressDao
) {
    fun getDecksForUser(userId: Long): Flow<List<DeckEntity>> {
        return deckDao.getDecksByUserId(userId)
    }

    suspend fun getDeckById(deckId: Long): DeckEntity? {
        return deckDao.getDeckById(deckId)
    }

    suspend fun createDeck(userId: Long, name: String, description: String, tags: String): Long {
        val deck = DeckEntity(
            userId = userId,
            name = name,
            description = description,
            tags = tags
        )
        return deckDao.insertDeck(deck)
    }

    suspend fun updateDeck(deck: DeckEntity) {
        deckDao.updateDeck(deck)
    }

    suspend fun deleteDeck(deck: DeckEntity) {
        deckDao.deleteDeck(deck)
    }

    fun getWordsForDeck(deckId: Long): Flow<List<WordEntity>> {
        return wordDao.getWordsByDeckId(deckId)
    }

    fun getWordsCountInDeck(deckId: Long): Flow<Int> {
        return wordDao.getWordsCountInDeck(deckId)
    }

    suspend fun getWordById(wordId: Long): WordEntity? {
        return wordDao.getWordById(wordId)
    }

    suspend fun addWord(word: WordEntity): Long {
        val wordId = wordDao.insertWord(word)
        val deck = deckDao.getDeckById(word.deckId)
        if (deck != null) {
            // Initialize learning progress for this word
            val existingProgress = progressDao.getProgressByWordAndUser(deck.userId, wordId)
            if (existingProgress == null) {
                progressDao.insertProgress(
                    ProgressEntity(
                        userId = deck.userId,
                        wordId = wordId,
                        status = "NEW"
                    )
                )
            }
        }
        return wordId
    }

    suspend fun updateWord(word: WordEntity) {
        wordDao.updateWord(word)
    }

    suspend fun deleteWord(word: WordEntity) {
        wordDao.deleteWord(word)
    }

    suspend fun importFromCSV(csvContent: String, deckId: Long): Result<Int> {
        return try {
            val deck = deckDao.getDeckById(deckId) ?: return Result.failure(Exception("Không tìm thấy bộ từ vựng"))
            val words = CSVHelper.parseCSV(csvContent, deckId)
            if (words.isEmpty()) {
                return Result.success(0)
            }
            
            for (word in words) {
                val wordId = wordDao.insertWord(word)
                // Initialize learning progress for each word
                val existingProgress = progressDao.getProgressByWordAndUser(deck.userId, wordId)
                if (existingProgress == null) {
                    progressDao.insertProgress(
                        ProgressEntity(
                            userId = deck.userId,
                            wordId = wordId,
                            status = "NEW"
                        )
                    )
                }
            }
            Result.success(words.size)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun exportToCSV(deckId: Long): Result<String> {
        return try {
            val words = wordDao.getWordsByDeckId(deckId).first()
            val csvString = CSVHelper.exportToCSV(words)
            Result.success(csvString)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Pre-populates the database with sample decks and words for a new user.
     */
    suspend fun prepopulateSampleData(userId: Long) {
        val existingDecks = getDecksForUser(userId).first()
        if (existingDecks.isNotEmpty()) return

        // 1. Education & Academic
        val educationId = createDeck(
            userId = userId,
            name = "🎓 Education & Academic",
            description = "Từ vựng về giáo dục, nghiên cứu, học thuật",
            tags = "Education, Academic, IELTS"
        )
        for (word in SampleData.getEducationWords(educationId)) {
            addWord(word)
        }

        // 2. Business & Finance
        val businessId = createDeck(
            userId = userId,
            name = "💼 Business & Finance",
            description = "Từ vựng kinh doanh, tài chính, thương mại",
            tags = "Business, Finance, TOEIC"
        )
        for (word in SampleData.getBusinessWords(businessId)) {
            addWord(word)
        }

        // 3. Environment & Nature
        val environmentId = createDeck(
            userId = userId,
            name = "🌍 Environment & Nature",
            description = "Từ vựng môi trường, thiên nhiên, khí hậu",
            tags = "Environment, Nature, IELTS"
        )
        for (word in SampleData.getEnvironmentWords(environmentId)) {
            addWord(word)
        }

        // 4. Health & Lifestyle
        val healthId = createDeck(
            userId = userId,
            name = "🏥 Health & Lifestyle",
            description = "Từ vựng sức khỏe, đời sống, y tế",
            tags = "Health, Lifestyle"
        )
        for (word in SampleData.getHealthWords(healthId)) {
            addWord(word)
        }

        // 5. Technology & Innovation
        val technologyId = createDeck(
            userId = userId,
            name = "💻 Technology & Innovation",
            description = "Từ vựng công nghệ, đổi mới, kỹ thuật số",
            tags = "Technology, Innovation"
        )
        for (word in SampleData.getTechnologyWords(technologyId)) {
            addWord(word)
        }

        // 6. Travel & Communication
        val travelId = createDeck(
            userId = userId,
            name = "✈️ Travel & Communication",
            description = "Từ vựng du lịch, giao tiếp, di chuyển",
            tags = "Travel, Communication"
        )
        for (word in SampleData.getTravelWords(travelId)) {
            addWord(word)
        }

        // 7. Work & Office
        val workId = createDeck(
            userId = userId,
            name = "🏢 Work & Office",
            description = "Từ vựng công sở, tuyển dụng, quản lý",
            tags = "Work, Office, TOEIC"
        )
        for (word in SampleData.getWorkWords(workId)) {
            addWord(word)
        }

        // 8. Society & Government
        val societyId = createDeck(
            userId = userId,
            name = "⚖️ Society & Government",
            description = "Từ vựng xã hội, chính trị, pháp luật",
            tags = "Society, Government, IELTS"
        )
        for (word in SampleData.getSocietyWords(societyId)) {
            addWord(word)
        }
    }
}
