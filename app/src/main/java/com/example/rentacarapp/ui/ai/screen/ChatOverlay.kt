package com.example.rentacarapp.ui.ai.screen


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rentacarapp.R
import com.example.rentacarapp.ui.ai.viewModel.AiChatUiState
import com.example.rentacarapp.ui.ai.viewModel.ChatMessage
import com.example.rentacarapp.ui.ai.viewModel.MainViewModel

@Composable
fun ChatOverlay(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ChatOverlayContent(
        uiState = uiState,
        onToggleChat = { viewModel.toggleChat() },
        onSendMessage = { viewModel.sendMessage(it) }
    )
}

@Composable
fun ChatOverlayContent(
    uiState: AiChatUiState,
    onToggleChat: () -> Unit,
    onSendMessage: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomStart
    )
    {
        AnimatedVisibility(
            visible = uiState.isChatOpen,
            enter = slideInVertically { it } + fadeIn(),
            exit = slideOutVertically { it } + fadeOut(),
            modifier = Modifier.padding(bottom = 80.dp)
        )
        {
            ChatWindow(
                messages = uiState.messages,
                isLoading = uiState.isLoading,
                error = uiState.error,
                onSendMessage = onSendMessage,
                close = onToggleChat
            )
        }

        FloatingActionButton(
            onClick = onToggleChat,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
                .size(64.dp)
        ) {
            Icon(
                imageVector = if (uiState.isChatOpen) Icons.Default.Close else Icons.Default.SmartToy,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun ChatWindow(
    messages: List<ChatMessage>,
    isLoading: Boolean,
    error: String?,
    onSendMessage: (String) -> Unit,
    close: () -> Unit
) {
    var inputText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val focus = LocalFocusManager.current

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) listState.animateScrollToItem(messages.size - 1)
    }

    Card(
        modifier = Modifier
            .width(320.dp)
            .height(450.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(12.dp),
            )

            {
                Text(
                    text = stringResource(R.string.aiAssistant),
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.CenterStart)
                )

                IconButton(
                    onClick = close,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
                {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(messages) { msg -> ChatBubble(msg) }

                if (isLoading) {
                    item {
                        Text(
                            text = "Typing...",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
                if (error != null) {
                    item {
                        Text(
                            "Error: $error",
                            color = Color.Red
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text(text = "Ask...") },
                    shape = RoundedCornerShape(8.dp)
                )
                IconButton(onClick = {
                    if (inputText.isNotBlank()) {
                        onSendMessage(inputText)
                        inputText = ""
                        focus.clearFocus()
                    }
                }) {
                    Icon(
                        Icons.AutoMirrored.Filled.Send,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun ChatBubble(
    message: ChatMessage
) {
    val isUser = message.isUser
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (isUser) Alignment.CenterEnd else Alignment.CenterStart
    )
    {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = if (isUser) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer

        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(8.dp),
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatOverLayContentPreview() {
    val mockState = AiChatUiState(
        isChatOpen = true,
        messages = listOf(
            ChatMessage("Hello", true),
            ChatMessage("Hi!, how can I help?", false)
        )
    )
    ChatOverlayContent(
        uiState = mockState,
        onToggleChat = {},
        onSendMessage = {}
    )
}