```kotlin
package com.offlinellm

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var inputField: EditText
    private lateinit var sendButton: Button
    private lateinit var chatAdapter: ChatAdapter
    private val chatMessages = mutableListOf<ChatMessage>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        recyclerView = findViewById(R.id.recyclerView)
        inputField = findViewById(R.id.inputField)
        sendButton = findViewById(R.id.sendButton)
        
        chatAdapter = ChatAdapter(chatMessages)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = chatAdapter
        
        sendButton.setOnClickListener {
            val message = inputField.text.toString().trim()
            if (message.isNotEmpty()) {
                sendMessage(message)
            }
        }
        
        // 欢迎消息
        chatMessages.add(ChatMessage("你好！我是离线AI助手。输入你的问题吧。", isUser = false))
        chatAdapter.notifyDataSetChanged()
    }
    
    private fun sendMessage(message: String) {
        chatMessages.add(ChatMessage(message, isUser = true))
        chatAdapter.notifyItemInserted(chatMessages.size - 1)
        inputField.text.clear()
        recyclerView.scrollToPosition(chatMessages.size - 1)
        
        // 模拟AI回复（演示用）
        // 实际可以集成 TensorFlow Lite 或其他推理引擎
        CoroutineScope(Dispatchers.Main).launch {
            val reply = generateReply(message)
            chatMessages.add(ChatMessage(reply, isUser = false))
            chatAdapter.notifyItemInserted(chatMessages.size - 1)
            recyclerView.scrollToPosition(chatMessages.size - 1)
        }
    }
    
    private suspend fun generateReply(input: String): String {
        delay(1000) // 模拟推理延迟
        return when {
            input.contains("你好") -> "你好！有什么我可以帮你的吗？"
            input.contains("名字") -> "我是离线AI助手，运行在你的手机上！"
            input.contains("离线") -> "我完全离线运行，不需要联网哦。"
            else -> "收到你的消息：「$input」。这是一个离线演示版本。要获得完整AI能力，需要集成TensorFlow Lite和模型文件。"
        }
    }
}

data class ChatMessage(val text: String, val isUser: Boolean)
```

---





































































