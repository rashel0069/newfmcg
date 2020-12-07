package com.appshat.fmcgapp.Kotlin.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appshat.fmcgapp.Kotlin.data.Message
import com.appshat.fmcgapp.Kotlin.utils.Constants.RECEIVE_ID
import com.appshat.fmcgapp.Kotlin.utils.Constants.SEND_ID
import com.appshat.fmcgapp.R
import kotlinx.android.synthetic.main.message_item.view.*

class MessagingAdapter : RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {
    var messagesList = mutableListOf<Message>()

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                //Remove message on the item clicked
                messagesList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {

        return MessageViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        )

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messagesList[position]

        when (currentMessage.id) {
            SEND_ID -> {
                holder.itemView.tv_message.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_bot_message.visibility = View.GONE
            }
            RECEIVE_ID -> {
                holder.itemView.tv_bot_message.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_message.visibility = View.GONE
            }
        }
    }

    fun insertMessage(message: Message) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }



    override fun getItemCount(): Int {
        return messagesList.size
    }

    }
