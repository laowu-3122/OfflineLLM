```kotlin
package com.offlinellm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val messages: List<ChatMessage>) : 
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messages[position]
        holder.messageText.text = message.text
        
        if (message.isUser) {
            holder.messageText.setBackgroundResource(R.drawable.user_message_bg)
            holder.messageText.setTextColor(0xFFFFFFFF.toInt())
        } else {
            holder.messageText.setBackgroundResource(R.drawable.bot_message_bg)
            holder.messageText.setTextColor(0xFF000000.toInt())
        }
    }
    
    override fun getItemCount() = messages.size
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageText: TextView = itemView.findViewById(R.id.messageText)
    }
}
```




































