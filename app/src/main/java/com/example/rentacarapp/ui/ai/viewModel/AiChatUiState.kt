package com.example.rentacarapp.ui.ai.viewModel

data class AiChatUiState (
    val messages:List<ChatMessage> = emptyList(),
    val isLoading: Boolean = false,
    val error: String?=null,
    val isChatOpen: Boolean = false
)

data class ChatMessage(
    val text :String,
    val isUser: Boolean,
    val timeStamp:Long = System.currentTimeMillis()
)