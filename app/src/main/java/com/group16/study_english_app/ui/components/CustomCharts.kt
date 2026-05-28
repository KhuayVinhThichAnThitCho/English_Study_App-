package com.group16.study_english_app.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group16.study_english_app.data.local.entity.ActivityLogEntity
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun DailyActivityBarChart(
    logs: List<ActivityLogEntity>,
    modifier: Modifier = Modifier
) {
    val barColor = MaterialTheme.colorScheme.primary
    val barAccentColor = MaterialTheme.colorScheme.secondary
    val labelColor = MaterialTheme.colorScheme.onSurfaceVariant
    
    // Animation progress
    val animationProgress = remember { Animatable(0f) }
    LaunchedEffect(logs) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000)
        )
    }

    val maxCount = remember(logs) {
        val maxVal = logs.maxOfOrNull { it.count } ?: 0
        if (maxVal == 0) 10 else maxVal
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Hoạt động học tập (7 ngày gần nhất)",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                
                val paddingLeft = 40f
                val paddingBottom = 60f
                val chartWidth = canvasWidth - paddingLeft
                val chartHeight = canvasHeight - paddingBottom

                // Draw Y-Axis grid lines & labels
                val gridLines = 3
                for (i in 0..gridLines) {
                    val y = chartHeight - (chartHeight / gridLines) * i
                    val gridVal = (maxCount / gridLines.toFloat() * i).toInt()
                    
                    // Draw grid line
                    drawLine(
                        color = Color.LightGray.copy(alpha = 0.4f),
                        start = Offset(paddingLeft, y),
                        end = Offset(canvasWidth, y),
                        strokeWidth = 2f
                    )
                    
                    // Draw Y text
                    drawContext.canvas.nativeCanvas.drawText(
                        gridVal.toString(),
                        10f,
                        y + 10f,
                        android.graphics.Paint().apply {
                            color = labelColor.hashCode()
                            textSize = 28f
                            textAlign = android.graphics.Paint.Align.LEFT
                        }
                    )
                }

                if (logs.isNotEmpty()) {
                    val barWidth = (chartWidth / logs.size) * 0.5f
                    val spacing = (chartWidth / logs.size) * 0.5f

                    logs.forEachIndexed { index, log ->
                        val ratio = log.count.toFloat() / maxCount
                        val animRatio = ratio * animationProgress.value
                        val barHeight = chartHeight * animRatio
                        
                        val x = paddingLeft + spacing + index * (barWidth + spacing)
                        val y = chartHeight - barHeight

                        // Draw Rounded Bar
                        drawRoundRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(barAccentColor, barColor)
                            ),
                            topLeft = Offset(x, y),
                            size = Size(barWidth, barHeight),
                            cornerRadius = CornerRadius(10f, 10f)
                        )

                        // Draw count on top of bar
                        if (log.count > 0 && animationProgress.value > 0.8f) {
                            drawContext.canvas.nativeCanvas.drawText(
                                log.count.toString(),
                                x + barWidth / 2f,
                                y - 10f,
                                android.graphics.Paint().apply {
                                    color = barColor.hashCode()
                                    textSize = 26f
                                    textAlign = android.graphics.Paint.Align.CENTER
                                    isFakeBoldText = true
                                }
                            )
                        }

                        // Draw X Labels (e.g. 28/05)
                        val inputSdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        val outputSdf = SimpleDateFormat("dd/MM", Locale.getDefault())
                        val labelText = try {
                            val date = inputSdf.parse(log.dateString)
                            if (date != null) outputSdf.format(date) else ""
                        } catch (e: Exception) {
                            log.dateString.takeLast(5)
                        }

                        drawContext.canvas.nativeCanvas.drawText(
                            labelText,
                            x + barWidth / 2f,
                            chartHeight + 40f,
                            android.graphics.Paint().apply {
                                color = labelColor.hashCode()
                                textSize = 26f
                                textAlign = android.graphics.Paint.Align.CENTER
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RetentionRateLineChart(
    logs: List<ActivityLogEntity>, // We can simulate retention rate based on daily active streak and progress
    modifier: Modifier = Modifier
) {
    val primaryColor = Color(0xFF10B981) // Emerald Green for retention
    val accentColor = Color(0xFF34D399)
    val labelColor = MaterialTheme.colorScheme.onSurfaceVariant
    
    // Animation progress
    val animationProgress = remember { Animatable(0f) }
    LaunchedEffect(logs) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1200)
        )
    }

    // Generate retention points: based on the active days we simulate retention curve (80% to 95%)
    val retentionPoints = remember(logs) {
        val points = mutableListOf<Float>()
        var baseRetention = 85f // Starts at 85%
        logs.forEach { log ->
            if (log.count > 0) {
                baseRetention = (baseRetention + 3f).coerceAtMost(98f) // learning increases retention
            } else {
                baseRetention = (baseRetention - 2f).coerceAtLeast(60f) // skipping decreases retention
            }
            points.add(baseRetention)
        }
        if (points.isEmpty()) {
            listOf(80f, 82f, 85f, 83f, 88f, 90f, 92f)
        } else points
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Tỷ lệ ghi nhớ ước tính (%)",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                
                val paddingLeft = 60f
                val paddingBottom = 60f
                val chartWidth = canvasWidth - paddingLeft
                val chartHeight = canvasHeight - paddingBottom

                // Draw Y labels (50%, 75%, 100%)
                val YLevels = listOf(50f, 75f, 100f)
                YLevels.forEach { percentage ->
                    val y = chartHeight - (percentage / 100f) * chartHeight
                    
                    drawLine(
                        color = Color.LightGray.copy(alpha = 0.3f),
                        start = Offset(paddingLeft, y),
                        end = Offset(canvasWidth, y),
                        strokeWidth = 2f
                    )
                    
                    drawContext.canvas.nativeCanvas.drawText(
                        "${percentage.toInt()}%",
                        10f,
                        y + 10f,
                        android.graphics.Paint().apply {
                            color = labelColor.hashCode()
                            textSize = 26f
                            textAlign = android.graphics.Paint.Align.LEFT
                        }
                    )
                }

                if (retentionPoints.isNotEmpty()) {
                    val stepX = chartWidth / (retentionPoints.size - 1)
                    val points = retentionPoints.mapIndexed { index, pct ->
                        val x = paddingLeft + index * stepX
                        // Map 0-100% to chart height
                        val y = chartHeight - (pct / 100f) * chartHeight
                        Offset(x, y)
                    }

                    // Build path for Bezier Curve
                    val path = Path()
                    val backgroundPath = Path()
                    
                    if (points.isNotEmpty()) {
                        path.moveTo(points[0].x, points[0].y)
                        backgroundPath.moveTo(points[0].x, chartHeight)
                        backgroundPath.lineTo(points[0].x, points[0].y)

                        for (i in 1 until points.size) {
                            val prevPoint = points[i - 1]
                            val currentPoint = points[i]
                            
                            // Bezier control points
                            val controlX1 = prevPoint.x + (currentPoint.x - prevPoint.x) / 2f
                            val controlY1 = prevPoint.y
                            val controlX2 = prevPoint.x + (currentPoint.x - prevPoint.x) / 2f
                            val controlY2 = currentPoint.y

                            path.cubicTo(
                                controlX1, controlY1,
                                controlX2, controlY2,
                                currentPoint.x, currentPoint.y
                            )
                            
                            backgroundPath.cubicTo(
                                controlX1, controlY1,
                                controlX2, controlY2,
                                currentPoint.x, currentPoint.y
                            )
                        }
                        
                        backgroundPath.lineTo(points.last().x, chartHeight)
                        backgroundPath.close()

                        // Draw background gradient (fade under the line)
                        val animatedAlpha = animationProgress.value
                        drawPath(
                            path = backgroundPath,
                            brush = Brush.verticalGradient(
                                colors = listOf(accentColor.copy(alpha = 0.3f * animatedAlpha), Color.Transparent)
                            )
                        )

                        // Draw path stroke with animation
                        // Using a clip modifier isn't native, so we can draw path with custom animation progress on X
                        val animatedPath = Path()
                        animatedPath.moveTo(points[0].x, points[0].y)
                        for (i in 1 until points.size) {
                            val prevPoint = points[i - 1]
                            val currentPoint = points[i]
                            
                            val t = animationProgress.value
                            val currentX = prevPoint.x + (currentPoint.x - prevPoint.x) * t
                            val currentY = prevPoint.y + (currentPoint.y - prevPoint.y) * t
                            
                            val controlX1 = prevPoint.x + (currentX - prevPoint.x) / 2f
                            val controlY1 = prevPoint.y
                            val controlX2 = prevPoint.x + (currentX - prevPoint.x) / 2f
                            val controlY2 = currentY
                            
                            animatedPath.cubicTo(
                                controlX1, controlY1,
                                controlX2, controlY2,
                                currentX, currentY
                            )
                        }

                        drawPath(
                            path = path,
                            color = primaryColor,
                            style = Stroke(width = 6f, cap = StrokeCap.Round)
                        )
                        
                        // Draw point circles
                        points.forEachIndexed { index, point ->
                            drawCircle(
                                color = primaryColor,
                                radius = 8f,
                                center = point
                            )
                            drawCircle(
                                color = Color.White,
                                radius = 4f,
                                center = point
                            )
                            
                            // Draw value label above point
                            if (index == points.size - 1 && animationProgress.value > 0.9f) {
                                drawContext.canvas.nativeCanvas.drawText(
                                    "${retentionPoints[index].toInt()}%",
                                    point.x,
                                    point.y - 15f,
                                    android.graphics.Paint().apply {
                                        color = primaryColor.hashCode()
                                        textSize = 28f
                                        textAlign = android.graphics.Paint.Align.CENTER
                                        isFakeBoldText = true
                                    }
                                )
                            }
                        }

                        // Draw X dates
                        logs.forEachIndexed { index, log ->
                            val x = paddingLeft + index * stepX
                            val inputSdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val outputSdf = SimpleDateFormat("dd/MM", Locale.getDefault())
                            val labelText = try {
                                val date = inputSdf.parse(log.dateString)
                                if (date != null) outputSdf.format(date) else ""
                            } catch (e: Exception) {
                                log.dateString.takeLast(5)
                            }

                            drawContext.canvas.nativeCanvas.drawText(
                                labelText,
                                x,
                                chartHeight + 40f,
                                android.graphics.Paint().apply {
                                    color = labelColor.hashCode()
                                    textSize = 26f
                                    textAlign = android.graphics.Paint.Align.CENTER
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
