package com.group16.study_english_app.ui.components

import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

@Composable
fun rememberTextToSpeech(): (String) -> Unit {
    val context = LocalContext.current
    
    val ttsState = remember {
        val state = object {
            var tts: TextToSpeech? = null
            var isReady = false
        }
        state.tts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = state.tts?.setLanguage(Locale.US)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language US is not supported or missing data, falling back to default locale")
                    state.tts?.language = Locale.getDefault()
                }
                state.isReady = true
                Log.d("TTS", "TTS Initialized successfully")
            } else {
                Log.e("TTS", "Initialization failed with status $status")
            }
        }
        state
    }

    DisposableEffect(Unit) {
        onDispose {
            ttsState.tts?.shutdown()
        }
    }

    return { text ->
        if (ttsState.isReady) {
            val result = ttsState.tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "MinLishTTS")
            if (result == TextToSpeech.ERROR) {
                Log.e("TTS", "Error speaking text: $text")
                Toast.makeText(context, "Lỗi phát âm: vui lòng kiểm tra cài đặt TTS trên máy!", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("TTS", "Speaking text: $text")
            }
        } else {
            Log.w("TTS", "TTS is not ready yet")
            Toast.makeText(context, "Bộ phát âm đang khởi động, vui lòng thử lại sau 2 giây!", Toast.LENGTH_SHORT).show()
        }
    }
}
