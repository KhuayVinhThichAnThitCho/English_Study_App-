package com.group16.study_english_app.data.util

import com.group16.study_english_app.data.local.entity.WordEntity

object CSVHelper {

    fun parseCSV(content: String, deckId: Long): List<WordEntity> {
        val cleanContent = if (content.startsWith("\uFEFF")) content.substring(1) else content
        val result = mutableListOf<WordEntity>()
        // Kiểm tra chữ ký file Zip/Office Open XML (PK...) hoặc chứa ký tự null byte để chặn file Excel nhị phân
        if (cleanContent.startsWith("PK\u0003\u0004") || cleanContent.startsWith("PK") || cleanContent.contains("\u0000")) {
            throw IllegalArgumentException("Định dạng file không hợp lệ! Bạn đang chọn một file Excel (.xlsx / .xls) hoặc file nén. Vui lòng mở Excel và lưu tệp dưới định dạng CSV (chọn File -> Save As -> CSV UTF-8) trước khi nhập vào ứng dụng.")
        }
        val lines = cleanContent.lines()
        if (lines.isEmpty()) return result

        var isFirstLine = true
        for (line in lines) {
            if (line.trim().isEmpty()) continue
            
            val row = parseCsvLine(line)
            
            // Skip header if it matches the keywords
            if (isFirstLine && row.isNotEmpty() && (row[0].equals("word", ignoreCase = true) || row[0].equals("từ", ignoreCase = true))) {
                isFirstLine = false
                continue
            }
            isFirstLine = false

            if (row.isEmpty()) continue

            // Pad the row to 8 columns if it has fewer
            val word = row.getOrNull(0) ?: ""
            if (word.isBlank()) continue // Word must not be blank

            val pronunciation = row.getOrNull(1) ?: ""
            val meaning = row.getOrNull(2) ?: ""
            val descriptionEn = row.getOrNull(3) ?: ""
            val example = row.getOrNull(4) ?: ""
            val collocation = row.getOrNull(5) ?: ""
            val relatedWords = row.getOrNull(6) ?: ""
            val note = row.getOrNull(7) ?: ""

            result.add(
                WordEntity(
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
            )
        }
        return result
    }

    /**
     * Serializes a list of WordEntities to a CSV string.
     */
    fun exportToCSV(words: List<WordEntity>): String {
        val sb = StringBuilder()
        // Header
        sb.append("Word,Pronunciation,Meaning,Description,Example,Collocation,RelatedWords,Note\n")
        
        for (word in words) {
            sb.append(escapeCsv(word.word)).append(",")
            sb.append(escapeCsv(word.pronunciation)).append(",")
            sb.append(escapeCsv(word.meaning)).append(",")
            sb.append(escapeCsv(word.descriptionEn)).append(",")
            sb.append(escapeCsv(word.example)).append(",")
            sb.append(escapeCsv(word.collocation)).append(",")
            sb.append(escapeCsv(word.relatedWords)).append(",")
            sb.append(escapeCsv(word.note)).append("\n")
        }
        return sb.toString()
    }

    private fun parseCsvLine(line: String): List<String> {
        val row = mutableListOf<String>()
        var inQuotes = false
        val sb = StringBuilder()
        var i = 0
        while (i < line.length) {
            val c = line[i]
            if (c == '"') {
                // Check if it is a double quote representing a single quote inside quotes
                if (inQuotes && i + 1 < line.length && line[i + 1] == '"') {
                    sb.append('"')
                    i++ // Skip the second quote
                } else {
                    inQuotes = !inQuotes
                }
            } else if (c == ',' && !inQuotes) {
                row.add(sb.toString().trim())
                sb.setLength(0)
            } else {
                sb.append(c)
            }
            i++
        }
        row.add(sb.toString().trim())
        return row
    }

    private fun escapeCsv(value: String): String {
        val clean = value.replace("\"", "\"\"")
        return if (clean.contains(",") || clean.contains("\n") || clean.contains("\"")) {
            "\"$clean\""
        } else {
            clean
        }
    }
}
