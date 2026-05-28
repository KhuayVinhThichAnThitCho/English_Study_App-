package com.group16.study_english_app.ui.screens.practice

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group16.study_english_app.data.local.entity.UserEntity
import com.group16.study_english_app.ui.viewmodel.PracticeViewModel
import com.group16.study_english_app.ui.viewmodel.QuizType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    deckId: Long,
    practiceViewModel: PracticeViewModel,
    userEntity: UserEntity?,
    onBack: () -> Unit
) {
    val user = userEntity ?: return
    val questions by practiceViewModel.quizQuestions.collectAsState()
    val currentIndex by practiceViewModel.currentQuestionIndex.collectAsState()
    val selectedOptionIndex by practiceViewModel.selectedAnswerIndex.collectAsState()
    val typedAnswer by practiceViewModel.typedAnswer.collectAsState()
    val correctCount by practiceViewModel.correctCount.collectAsState()
    val isQuizFinished by practiceViewModel.isQuizFinished.collectAsState()

    val focusManager = LocalFocusManager.current
    var typingInput by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }

    LaunchedEffect(deckId) {
        practiceViewModel.startQuiz(deckId)
    }

    // Reset local typing state when question index changes
    LaunchedEffect(currentIndex) {
        typingInput = ""
        isSubmitted = false
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            TopAppBar(
                title = { Text("Luyện Tập Trắc Nghiệm", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            when {
                questions.isEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Đang tạo câu hỏi trắc nghiệm...", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
                isQuizFinished -> {
                    // Result Summary Screen
                    QuizResultView(
                        correctCount = correctCount,
                        totalCount = questions.size,
                        onBack = onBack
                    )
                }
                else -> {
                    val currentQuestion = questions.getOrNull(currentIndex)
                    if (currentQuestion != null) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            
                            // Top progress bar
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Câu hỏi ${currentIndex + 1}/${questions.size}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = "Đúng: $correctCount",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color(0xFF10B981),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                LinearProgressIndicator(
                                    progress = (currentIndex.toFloat() / questions.size),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(6.dp)
                                        .clip(RoundedCornerShape(3.dp)),
                                    color = MaterialTheme.colorScheme.primary,
                                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                                )
                            }

                            // Question Title Card
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Text(
                                    text = currentQuestion.questionText,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(24.dp),
                                    textAlign = TextAlign.Center
                                )
                            }

                            // Answer Options Container
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                if (currentQuestion.type == QuizType.MULTIPLE_CHOICE) {
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(10.dp),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        currentQuestion.options.forEachIndexed { index, option ->
                                            val isSelected = selectedOptionIndex == index
                                            val isCorrectAnswer = index == currentQuestion.correctOptionIndex
                                            
                                            // Determine background color based on selection and answer status
                                            val cardColor = when {
                                                selectedOptionIndex != null -> {
                                                    when {
                                                        isCorrectAnswer -> Color(0xFFD1FAE5) // Soft green for correct answer
                                                        isSelected -> Color(0xFFFEE2E2) // Soft red for selected incorrect answer
                                                        else -> MaterialTheme.colorScheme.surface
                                                    }
                                                }
                                                else -> MaterialTheme.colorScheme.surface
                                            }

                                            val borderStroke = when {
                                                selectedOptionIndex != null -> {
                                                    when {
                                                        isCorrectAnswer -> Modifier.border(2.dp, Color(0xFF10B981), RoundedCornerShape(12.dp))
                                                        isSelected -> Modifier.border(2.dp, Color(0xFFEF4444), RoundedCornerShape(12.dp))
                                                        else -> Modifier
                                                    }
                                                }
                                                else -> Modifier
                                            }

                                            Card(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .then(borderStroke)
                                                    .clickable(enabled = selectedOptionIndex == null) {
                                                        practiceViewModel.submitMultipleChoice(index, user.id)
                                                    },
                                                shape = RoundedCornerShape(12.dp),
                                                colors = CardDefaults.cardColors(containerColor = cardColor),
                                                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                                            ) {
                                                Row(
                                                    modifier = Modifier.padding(16.dp),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Text(
                                                        text = "${('A' + index)}.  $option",
                                                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                                                        color = when {
                                                            selectedOptionIndex != null && isCorrectAnswer -> Color(0xFF065F46)
                                                            selectedOptionIndex != null && isSelected -> Color(0xFF991B1B)
                                                            else -> MaterialTheme.colorScheme.onSurface
                                                        }
                                                    )
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    // Typing Question View
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        OutlinedTextField(
                                            value = typingInput,
                                            onValueChange = { if (!isSubmitted) typingInput = it },
                                            label = { Text("Gõ từ tiếng Anh tại đây") },
                                            singleLine = true,
                                            readOnly = isSubmitted,
                                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                            keyboardActions = KeyboardActions(onDone = {
                                                if (typingInput.isNotBlank() && !isSubmitted) {
                                                    isSubmitted = true
                                                    practiceViewModel.submitTyping(typingInput, user.id)
                                                    focusManager.clearFocus()
                                                }
                                            }),
                                            modifier = Modifier.fillMaxWidth(),
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        
                                        Spacer(modifier = Modifier.height(16.dp))

                                        if (!isSubmitted) {
                                            Button(
                                                onClick = {
                                                    isSubmitted = true
                                                    practiceViewModel.submitTyping(typingInput, user.id)
                                                    focusManager.clearFocus()
                                                },
                                                enabled = typingInput.isNotBlank(),
                                                shape = RoundedCornerShape(12.dp),
                                                modifier = Modifier.fillMaxWidth().height(48.dp)
                                            ) {
                                                Text("Kiểm tra đáp án", fontWeight = FontWeight.Bold)
                                            }
                                        } else {
                                            // Feedback layout
                                            val isAnswerCorrect = typingInput.trim().equals(currentQuestion.word.word.trim(), ignoreCase = true)
                                            Card(
                                                modifier = Modifier.fillMaxWidth(),
                                                shape = RoundedCornerShape(12.dp),
                                                colors = CardDefaults.cardColors(
                                                    containerColor = if (isAnswerCorrect) Color(0xFFD1FAE5) else Color(0xFFFEE2E2)
                                                )
                                            ) {
                                                Row(
                                                    modifier = Modifier.padding(16.dp),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Icon(
                                                        imageVector = if (isAnswerCorrect) Icons.Default.CheckCircle else Icons.Default.Close,
                                                        contentDescription = null,
                                                        tint = if (isAnswerCorrect) Color(0xFF10B981) else Color(0xFFEF4444),
                                                        modifier = Modifier.size(28.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(12.dp))
                                                    Column {
                                                        Text(
                                                            text = if (isAnswerCorrect) "Chính xác!" else "Chưa chính xác!",
                                                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                                            color = if (isAnswerCorrect) Color(0xFF065F46) else Color(0xFF991B1B)
                                                        )
                                                        Text(
                                                            text = "Đáp án đúng: ${currentQuestion.word.word}",
                                                            style = MaterialTheme.typography.bodyMedium,
                                                            color = if (isAnswerCorrect) Color(0xFF065F46) else Color(0xFF991B1B)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            // Footer button (Next Question)
                            val showNextButton = selectedOptionIndex != null || isSubmitted
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                androidx.compose.animation.AnimatedVisibility(visible = showNextButton) {
                                    Button(
                                        onClick = {
                                            practiceViewModel.nextQuestion(user.id)
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(48.dp),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.primary
                                        )
                                    ) {
                                        Text("Tiếp Tục", fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuizResultView(
    correctCount: Int,
    totalCount: Int,
    onBack: () -> Unit
) {
    val accuracy = if (totalCount > 0) ((correctCount.toFloat() / totalCount) * 100).toInt() else 0

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.EmojiEvents,
                contentDescription = "Success",
                tint = Color(0xFFF59E0B),
                modifier = Modifier.size(96.dp)
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Text(
                text = "Hoàn Thành Bài Kiểm Tra!",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            // Score details
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "$correctCount/$totalCount",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Đáp án đúng",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Độ chính xác của bạn là $accuracy%. Lịch sử đã được lưu lại để theo dõi trong Dashboard.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Quay lại luyện tập",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}
