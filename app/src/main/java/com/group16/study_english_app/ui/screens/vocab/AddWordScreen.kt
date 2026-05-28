package com.group16.study_english_app.ui.screens.vocab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.group16.study_english_app.ui.viewmodel.VocabularyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWordScreen(
    deckId: Long,
    viewModel: VocabularyViewModel,
    onBack: () -> Unit
) {
    var word by remember { mutableStateOf("") }
    var pronunciation by remember { mutableStateOf("") }
    var meaning by remember { mutableStateOf("") }
    var descriptionEn by remember { mutableStateOf("") }
    var example by remember { mutableStateOf("") }
    var collocation by remember { mutableStateOf("") }
    var relatedWords by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    var wordError by remember { mutableStateOf(false) }
    var meaningError by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            TopAppBar(
                title = { Text("Thêm Từ Vựng Mới", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            
            // Word Input (Required)
            OutlinedTextField(
                value = word,
                onValueChange = { word = it; wordError = false },
                label = { Text("Từ tiếng Anh (Word) *") },
                isError = wordError,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            if (wordError) {
                Text(
                    text = "Không được để trống trường này",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Pronunciation Input
            OutlinedTextField(
                value = pronunciation,
                onValueChange = { pronunciation = it },
                label = { Text("Phiên âm (Pronunciation)") },
                placeholder = { Text("Ví dụ: /həˈləʊ/") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            // Meaning Input (Required)
            OutlinedTextField(
                value = meaning,
                onValueChange = { meaning = it; meaningError = false },
                label = { Text("Nghĩa tiếng Việt *") },
                isError = meaningError,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            if (meaningError) {
                Text(
                    text = "Không được để trống trường này",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Description En Input
            OutlinedTextField(
                value = descriptionEn,
                onValueChange = { descriptionEn = it },
                label = { Text("Giải thích tiếng Anh (English Description)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            // Example Input
            OutlinedTextField(
                value = example,
                onValueChange = { example = it },
                label = { Text("Ví dụ minh họa (Example sentence)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            // Collocation Input
            OutlinedTextField(
                value = collocation,
                onValueChange = { collocation = it },
                label = { Text("Collocation") },
                placeholder = { Text("Ví dụ: make a decision, take a photo") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            // Related Words Input
            OutlinedTextField(
                value = relatedWords,
                onValueChange = { relatedWords = it },
                label = { Text("Từ liên quan (Synonyms/Antonyms)") },
                placeholder = { Text("Ví dụ: fast -> quick, rapid") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            // Note Input
            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text("Ghi chú cá nhân (Notes)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Save Button
            Button(
                onClick = {
                    if (word.isBlank()) {
                        wordError = true
                    }
                    if (meaning.isBlank()) {
                        meaningError = true
                    }
                    
                    if (word.isNotBlank() && meaning.isNotBlank()) {
                        viewModel.addWord(
                            deckId = deckId,
                            word = word.trim(),
                            pronunciation = pronunciation.trim(),
                            meaning = meaning.trim(),
                            descriptionEn = descriptionEn.trim(),
                            example = example.trim(),
                            collocation = collocation.trim(),
                            relatedWords = relatedWords.trim(),
                            note = note.trim()
                        )
                        onBack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(Icons.Default.Save, contentDescription = "Save")
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Lưu Từ Vựng",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
