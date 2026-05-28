package com.group16.study_english_app.data.repository

import com.group16.study_english_app.data.local.dao.DeckDao
import com.group16.study_english_app.data.local.dao.ProgressDao
import com.group16.study_english_app.data.local.dao.WordDao
import com.group16.study_english_app.data.local.entity.DeckEntity
import com.group16.study_english_app.data.local.entity.ProgressEntity
import com.group16.study_english_app.data.local.entity.WordEntity
import com.group16.study_english_app.data.util.CSVHelper
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

        // IELTS Deck
        val ieltsId = createDeck(
            userId = userId,
            name = "IELTS Core Vocabulary",
            description = "Các từ vựng học thuật cốt lõi thường gặp trong bài thi IELTS",
            tags = "IELTS, Academic"
        )
        addWord(
            WordEntity(
                deckId = ieltsId,
                word = "Mitigate",
                pronunciation = "/ˈmɪt.ɪ.ɡeɪt/",
                meaning = "Làm nhẹ bớt, làm giảm bớt",
                descriptionEn = "Make something bad less severe, serious, or painful.",
                example = "Soil erosion was mitigated by the planting of trees.",
                collocation = "mitigate risks, mitigate effects",
                relatedWords = "alleviate, ease, reduce",
                note = "Từ này rất hay dùng trong IELTS Writing Task 2"
            )
        )
        addWord(
            WordEntity(
                deckId = ieltsId,
                word = "Ambiguous",
                pronunciation = "/æmˈbɪɡ.ju.əs/",
                meaning = "Mơ hồ, nhập nhằng",
                descriptionEn = "Open to more than one interpretation; not having one obvious meaning.",
                example = "His ambiguous answer left us all confused.",
                collocation = "ambiguous explanation, ambiguous language",
                relatedWords = "vague, unclear, cryptic",
                note = "Dùng khi nói về thông tin không rõ ràng"
            )
        )
        addWord(
            WordEntity(
                deckId = ieltsId,
                word = "Corroborate",
                pronunciation = "/kəˈrɒb.ə.reɪt/",
                meaning = "Xác minh, chứng thực",
                descriptionEn = "Confirm or give support to (a statement, theory, or finding).",
                example = "The witness corroborated the accused's statement.",
                collocation = "corroborate evidence, corroborate stories",
                relatedWords = "confirm, verify, validate",
                note = "Từ academic cấp độ cao C1/C2"
            )
        )
        addWord(
            WordEntity(
                deckId = ieltsId,
                word = "Pragmatic",
                pronunciation = "/præɡˈmæt.ɪk/",
                meaning = "Thực tế, thực dụng",
                descriptionEn = "Dealing with things sensibly and realistically based on practical considerations.",
                example = "We need a pragmatic approach to solve this issue.",
                collocation = "pragmatic approach, pragmatic decision",
                relatedWords = "practical, realistic, down-to-earth",
                note = "Ngược nghĩa với idealistic"
            )
        )

        // TOEIC Deck
        val toeicId = createDeck(
            userId = userId,
            name = "TOEIC Common Vocab",
            description = "Từ vựng công sở và giao dịch thương mại thông dụng",
            tags = "TOEIC, Business"
        )
        addWord(
            WordEntity(
                deckId = toeicId,
                word = "Negotiate",
                pronunciation = "/nəˈɡəʊ.ʃi.eɪt/",
                meaning = "Đàm phán, thương lượng",
                descriptionEn = "Obtain or bring about by discussion.",
                example = "We managed to negotiate a lower price.",
                collocation = "negotiate a contract, negotiate a deal",
                relatedWords = "compromise, bargain, discuss",
                note = "Chủ đề Business cực kỳ quan trọng"
            )
        )
        addWord(
            WordEntity(
                deckId = toeicId,
                word = "Implement",
                pronunciation = "/ˈmɪt.ɪ.ɡeɪt/", // typo fix: /ˈɪm.plɪ.ment/
                meaning = "Thi hành, triển khai",
                descriptionEn = "Put (a decision, plan, agreement, etc.) into effect.",
                example = "The changes will be implemented next month.",
                collocation = "implement changes, implement policies",
                relatedWords = "execute, enforce, apply",
                note = "Động từ thông dụng trong công việc"
            )
        )
        addWord(
            WordEntity(
                deckId = toeicId,
                word = "Collaborate",
                pronunciation = "/kəˈlæb.ə.reɪt/",
                meaning = "Hợp tác",
                descriptionEn = "Work jointly on an activity or project.",
                example = "Teams from both departments collaborated on the report.",
                collocation = "collaborate with partners, collaborate on project",
                relatedWords = "cooperate, unite, work together",
                note = "Dùng nhiều trong công việc nhóm"
            )
        )
    }
}
