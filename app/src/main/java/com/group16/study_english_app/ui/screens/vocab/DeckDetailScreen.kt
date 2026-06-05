package com.group16.study_english_app.ui.screens.vocab

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group16.study_english_app.data.local.entity.UserEntity
import com.group16.study_english_app.data.local.entity.WordEntity
import com.group16.study_english_app.ui.viewmodel.ResultState
import com.group16.study_english_app.ui.viewmodel.VocabularyViewModel
import java.io.BufferedReader
import java.io.InputStreamReader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckDetailScreen(
    deckId: Long,
    viewModel: VocabularyViewModel,
    userEntity: UserEntity?,
    onNavigateToAddWord: () -> Unit,
    onNavigateToQuiz: (Long) -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val currentDeck by viewModel.currentDeck.collectAsState()
    val words by viewModel.words.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var showImportDialog by remember { mutableStateOf(false) }
    var showExportDialog by remember { mutableStateOf(false) }

    val csvImportResult by viewModel.csvImportResult.collectAsState()
    val csvExportResult by viewModel.csvExportResult.collectAsState()

    LaunchedEffect(deckId) {
        viewModel.loadDeckDetails(deckId)
    }

    // Handle CSV Import feedback
    LaunchedEffect(csvImportResult) {
        when (csvImportResult) {
            is ResultState.Success -> {
                val count = (csvImportResult as ResultState.Success<Int>).data
                Toast.makeText(context, "Đã import thành công $count từ vựng!", Toast.LENGTH_SHORT).show()
                showImportDialog = false
                viewModel.resetCsvStates()
            }
            is ResultState.Error -> {
                val msg = (csvImportResult as ResultState.Error).message
                Toast.makeText(context, "Lỗi import: $msg", Toast.LENGTH_LONG).show()
                viewModel.resetCsvStates()
            }
            else -> {}
        }
    }

    // Launchers for system file picker
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            try {
                var fileName = ""
                context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                    val nameIndex = cursor.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
                    if (nameIndex != -1 && cursor.moveToFirst()) {
                        fileName = cursor.getString(nameIndex)
                    }
                }
                if (fileName.isBlank()) {
                    fileName = uri.path ?: ""
                }
                if (fileName.endsWith(".xlsx", ignoreCase = true) || fileName.endsWith(".xls", ignoreCase = true)) {
                    Toast.makeText(context, "Lỗi: Không hỗ trợ trực tiếp file Excel (.xlsx/.xls). Vui lòng lưu file dưới dạng CSV!", Toast.LENGTH_LONG).show()
                    return@rememberLauncherForActivityResult
                }

                val inputStream = context.contentResolver.openInputStream(uri)
                val reader = BufferedReader(InputStreamReader(inputStream, Charsets.UTF_8))
                val csvContent = reader.use { it.readText() }
                viewModel.importCSV(csvContent, deckId)
            } catch (e: Exception) {
                Toast.makeText(context, "Không thể đọc file CSV: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val fileSaverLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("text/comma-separated-values")
    ) { uri: Uri? ->
        if (uri != null) {
            try {
                val exportState = viewModel.csvExportResult.value
                val csvContent = (exportState as? ResultState.Success<String>)?.data ?: ""
                if (csvContent.isNotBlank()) {
                    val outputStream = context.contentResolver.openOutputStream(uri)
                    // Thêm BOM (\uFEFF) giúp Excel trên Windows nhận diện đúng mã UTF-8 của tiếng Việt có dấu
                    val bom = "\uFEFF"
                    outputStream?.use {
                        it.write(bom.toByteArray(Charsets.UTF_8))
                        it.write(csvContent.toByteArray(Charsets.UTF_8))
                    }
                    Toast.makeText(context, "Xuất file Excel thành công!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Lỗi khi lưu file: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val filteredWords = remember(words, searchQuery) {
        if (searchQuery.isBlank()) words
        else words.filter {
            it.word.contains(searchQuery, ignoreCase = true) ||
            it.meaning.contains(searchQuery, ignoreCase = true)
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            TopAppBar(
                title = { Text(currentDeck?.name ?: "Chi Tiết Bộ Từ", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    // Import CSV
                    IconButton(onClick = { showImportDialog = true }) {
                        Icon(Icons.Default.Upload, contentDescription = "Import CSV")
                    }
                    // Export CSV
                    IconButton(onClick = {
                        viewModel.exportCSV(deckId)
                        showExportDialog = true
                    }) {
                        Icon(Icons.Default.Download, contentDescription = "Export CSV")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAddWord,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Word")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Search Input
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Tìm kiếm từ vựng...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(12.dp)
            )

            // Start Quiz Button (if words >= 2)
            if (words.size >= 2) {
                Button(
                    onClick = { onNavigateToQuiz(deckId) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(
                        text = "Làm Quiz Trắc Nghiệm (${words.size} từ)",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }

            if (filteredWords.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (searchQuery.isNotBlank()) "Không tìm thấy từ vựng khớp" else "Bộ từ vựng này đang trống",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredWords) { word ->
                        WordItemCard(
                            word = word,
                            onDelete = { viewModel.deleteWord(word) }
                        )
                    }
                }
            }
        }

        // CSV Import Dialog
        if (showImportDialog) {
            AlertDialog(
                onDismissRequest = { showImportDialog = false },
                title = { Text("Import Từ Vựng", fontWeight = FontWeight.Bold) },
                text = {
                    Text(
                        text = "Vui lòng chọn file CSV chứa từ vựng từ thiết bị của bạn để tiến hành nhập dữ liệu.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                confirmButton = {
                    Button(
                        onClick = { filePickerLauncher.launch("text/*") },
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Chọn file CSV từ máy")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showImportDialog = false }) {
                        Text("Hủy")
                    }
                }
            )
        }

        // CSV Export Dialog
        if (showExportDialog) {
            AlertDialog(
                onDismissRequest = { showExportDialog = false },
                title = { Text("Xuất Dữ Liệu Bộ Từ", fontWeight = FontWeight.Bold) },
                text = {
                    val statusText = if (csvExportResult is ResultState.Success) {
                        "Dữ liệu bộ từ vựng đã sẵn sàng để xuất."
                    } else if (csvExportResult is ResultState.Error) {
                        "Lỗi khi chuẩn bị dữ liệu xuất."
                    } else {
                        "Đang chuẩn bị dữ liệu..."
                    }
                    Text(
                        text = "$statusText\n\nXuất dữ liệu từ vựng ra file CSV tương thích với Microsoft Excel (hỗ trợ đầy đủ tiếng Việt có dấu).",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            val defaultFileName = "MinLish_${currentDeck?.name ?: "Deck"}.csv"
                            fileSaverLauncher.launch(defaultFileName)
                            showExportDialog = false
                        },
                        enabled = csvExportResult is ResultState.Success,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Download, contentDescription = "Save", modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Lưu File Excel")
                        }
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showExportDialog = false }) {
                        Text("Đóng")
                    }
                }
            )
        }
    }
}

@Composable
fun WordItemCard(
    word: WordEntity,
    onDelete: () -> Unit
) {
    var showDeleteConfirm by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = word.word,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                    if (word.pronunciation.isNotBlank()) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Voice",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = word.pronunciation,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.secondary,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = word.meaning,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                if (word.descriptionEn.isNotBlank()) {
                    Text(
                        text = word.descriptionEn,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1
                    )
                }
            }

            IconButton(onClick = { showDeleteConfirm = true }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.error.copy(alpha = 0.6f)
                )
            }
        }
    }

    if (showDeleteConfirm) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirm = false },
            title = { Text("Xóa từ vựng?", fontWeight = FontWeight.Bold) },
            text = { Text("Bạn có chắc chắn muốn xóa từ '${word.word}' ra khỏi bộ từ vựng hiện tại không?") },
            confirmButton = {
                Button(
                    onClick = {
                        onDelete()
                        showDeleteConfirm = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Xóa", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirm = false }) {
                    Text("Hủy")
                }
            }
        )
    }
}
