package com.example.rentacarapp.ui.ai.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val generativeModel: GenerativeModel?
) : ViewModel() {

    private val _uiState = MutableStateFlow(AiChatUiState())
    val uiState: StateFlow<AiChatUiState> = _uiState.asStateFlow()

    fun toggleChat() {
        _uiState.update {
            it.copy(isChatOpen = !it.isChatOpen)
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun sendMessage(msgText: String) {
        clearError()
        if (generativeModel == null) {
            _uiState.update { current ->
                current.copy(
                    isLoading = false,
                    messages = current.messages + ChatMessage(
                        "Error api key is missind. Please generate a free key and configure local.properties.",
                        false
                    )
                )
            }
            return
        }
        if (msgText.isBlank()) return

        _uiState.update { currentState ->
            val userMsgText = ChatMessage(text = msgText, isUser = true)
            currentState.copy(
                messages = currentState.messages + userMsgText,
                isLoading = true,
                error = null
            )
        }
        viewModelScope.launch {
            try {
                val prompt = """You are a helpful assistant for a Rent-a-Car app.
                    User asks:"$msgText"
                    Answer briefly and professionally in English.
                """.trimMargin()
                val response = generativeModel.generateContent(prompt)
                val aiResponse = response.text ?: "I couldn't generate a response"
                _uiState.update { currentState ->
                    val aiMsg = ChatMessage(
                        text = aiResponse,
                        isUser = false
                    )
                    currentState.copy(
                        messages = currentState.messages + aiMsg,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Unknown error"
                    )
                }
            }
        }
    }
}