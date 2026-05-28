package com.group16.study_english_app.ui.screens.learning

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group16.study_english_app.data.local.entity.UserEntity
import com.group16.study_english_app.ui.components.Flashcard
import com.group16.study_english_app.ui.viewmodel.LearningViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningScreen(
    learningViewModel: LearningViewModel,
    userEntity: UserEntity?,
    onBack: () -> Unit
) {
    val queue by learningViewModel.learningQueue.collectAsState()
    val currentIndex by learningViewModel.currentIndex.collectAsState()
    val isFlipped by learningViewModel.isFlipped.collectAsState()
    val isFinished by learningViewModel.isFinished.collectAsState()
    val isLoading by learningViewModel.isLoading.collectAsState()

    val user = userEntity ?: return

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            TopAppBar(
                title = { Text("Spaced Repetition", fontWeight = FontWeight.Bold) },
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
                isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                isFinished || queue.isEmpty() -> {
                    // Session Completed Screen
                    SessionCompletedView(
                        totalWords = queue.size,
                        onBack = onBack
                    )
                }
                else -> {
                    val currentWord = queue.getOrNull(currentIndex)
                    if (currentWord != null) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            
                            // Top progress bar
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Thẻ số ${currentIndex + 1}/${queue.size}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = "${((currentIndex.toFloat() / queue.size) * 100).toInt()}% hoàn thành",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                LinearProgressIndicator(
                                    progress = (currentIndex.toFloat() / queue.size),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(8.dp)
                                        .clip(RoundedCornerShape(4.dp)),
                                    color = MaterialTheme.colorScheme.primary,
                                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Flashcard
                            Flashcard(
                                word = currentWord,
                                isFlipped = isFlipped,
                                onFlip = { learningViewModel.flipCard() },
                                modifier = Modifier.weight(1f)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Rating Feedback Buttons (Only visible when card is flipped)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(110.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                androidx.compose.animation.AnimatedVisibility(
                                    visible = isFlipped,
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = "Đánh giá mức độ ghi nhớ:",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.padding(bottom = 10.dp)
                                        )
                                        
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            // Again Button (q = 1)
                                            FeedbackButton(
                                                text = "Quên",
                                                color = Color(0xFFEF4444),
                                                onClick = {
                                                    learningViewModel.submitFeedback(user.id, currentWord.id, 1)
                                                },
                                                modifier = Modifier.weight(1f)
                                            )
                                            // Hard Button (q = 3)
                                            FeedbackButton(
                                                text = "Khó",
                                                color = Color(0xFFF59E0B),
                                                onClick = {
                                                    learningViewModel.submitFeedback(user.id, currentWord.id, 3)
                                                },
                                                modifier = Modifier.weight(1f)
                                            )
                                            // Good Button (q = 4)
                                            FeedbackButton(
                                                text = "Nhớ",
                                                color = Color(0xFF10B981),
                                                onClick = {
                                                    learningViewModel.submitFeedback(user.id, currentWord.id, 4)
                                                },
                                                modifier = Modifier.weight(1f)
                                            )
                                            // Easy Button (q = 5)
                                            FeedbackButton(
                                                text = "Dễ",
                                                color = Color(0xFF6366F1),
                                                onClick = {
                                                    learningViewModel.submitFeedback(user.id, currentWord.id, 5)
                                                },
                                                modifier = Modifier.weight(1f)
                                            )
                                        }
                                    }
                                }
                                
                                androidx.compose.animation.AnimatedVisibility(
                                    visible = !isFlipped,
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                                            .padding(horizontal = 16.dp, vertical = 8.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Info,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "Nhấp vào thẻ để lật mặt sau",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
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
fun FeedbackButton(
    text: String,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.height(48.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
    }
}

@Composable
fun SessionCompletedView(
    totalWords: Int,
    onBack: () -> Unit
) {
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
                imageVector = Icons.Default.Celebration,
                contentDescription = "Success",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(84.dp)
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Text(
                text = "Hoàn Thành Phiên Học!",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = if (totalWords > 0) {
                    "Tuyệt vời! Bạn đã hoàn thành học và ôn tập $totalWords từ vựng hôm nay bằng phương pháp lặp lại ngắt quãng (SM-2)."
                } else {
                    "Hôm nay bạn không có từ mới hay từ đến hạn ôn tập nào. Hãy tạo thêm từ mới hoặc quay lại vào ngày mai nhé!"
                },
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
                    text = "Quay lại Trang chủ",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}
