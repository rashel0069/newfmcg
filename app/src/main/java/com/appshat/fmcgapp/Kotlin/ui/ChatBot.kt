package com.appshat.fmcgapp.Kotlin.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.appshat.fmcgapp.Kotlin.data.Message
import com.appshat.fmcgapp.Kotlin.utils.BotResponse
import com.appshat.fmcgapp.Kotlin.utils.Constants.OPEN_GOOGLE
import com.appshat.fmcgapp.Kotlin.utils.Constants.OPEN_SEARCH
import com.appshat.fmcgapp.Kotlin.utils.Constants.RECEIVE_ID
import com.appshat.fmcgapp.Kotlin.utils.Constants.SEND_ID
import com.appshat.fmcgapp.Kotlin.utils.Time
import com.appshat.fmcgapp.R
import kotlinx.coroutines.*
import kotlinx.android.synthetic.main.activity_chat_bot.*
class ChatBot : AppCompatActivity() {
        private val TAG = "Chatbot"

        //You can ignore this messageList if you're coming from the tutorial,
        // it was used only for my personal debugging
        var messagesList = mutableListOf<Message>()

        private lateinit var adapter: MessagingAdapter
        private val botList = listOf("Peter", "Francesca", "Luigi", "Igor")

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_chat_bot)
            recyclerView()

            clickEvents()

            val random = (0..3).random()
            customBotMessage("Hello! Today you're speaking with ${botList[random]}, how may I help?")
        }

        private fun clickEvents() {

            //Send a message
            btn_send.setOnClickListener {
                sendMessage()
            }

            //Scroll back to correct position when user clicks on text view
            et_message.setOnClickListener {
                GlobalScope.launch {
                    delay(100)

                    withContext(Dispatchers.Main) {
                        rv_messages.scrollToPosition(adapter.itemCount - 1)

                    }
                }
            }
        }

        private fun recyclerView() {
            adapter = MessagingAdapter()
            rv_messages.adapter = adapter
            rv_messages.layoutManager = LinearLayoutManager(applicationContext)

        }

        override fun onStart() {
            super.onStart()
            //In case there are messages, scroll to bottom when re-opening app
            GlobalScope.launch {
                delay(100)
                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }

        private fun sendMessage() {
            val message = et_message.text.toString()
            val timeStamp = Time.timeStamp()

            if (message.isNotEmpty()) {
                //Adds it to our local list
                messagesList.add(Message(message, SEND_ID, timeStamp))
                et_message.setText("")

                adapter.insertMessage(Message(message, SEND_ID, timeStamp))
                rv_messages.scrollToPosition(adapter.itemCount - 1)

                botResponse(message)
            }
        }

        private fun botResponse(message: String) {
            val timeStamp = Time.timeStamp()

            GlobalScope.launch {
                //Fake response delay
                delay(1000)

                withContext(Dispatchers.Main) {
                    //Gets the response
                    val response = BotResponse.basicResponses(message)

                    //Adds it to our local list
                    messagesList.add(Message(response, RECEIVE_ID, timeStamp))

                    //Inserts our message into the adapter
                    adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))

                    //Scrolls us to the position of the latest message
                    rv_messages.scrollToPosition(adapter.itemCount - 1)

                    //Starts Google
                    when (response) {
                        OPEN_GOOGLE -> {
                            val site = Intent(Intent.ACTION_VIEW)
                            site.data = Uri.parse("https://www.google.com/")
                            startActivity(site)
                        }
                        OPEN_SEARCH -> {
                            val site = Intent(Intent.ACTION_VIEW)
                            val searchTerm: String? = message.substringAfterLast("search")
                            site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                            startActivity(site)
                        }

                    }
                }
            }
        }

        private fun customBotMessage(message: String) {

            GlobalScope.launch {
                delay(1000)
                withContext(Dispatchers.Main) {
                    val timeStamp = Time.timeStamp()
                    messagesList.add(Message(message, RECEIVE_ID, timeStamp))
                    adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                    rv_messages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }