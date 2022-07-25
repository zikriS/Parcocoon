package com.example.projectkpbaru.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkpbaru.Message
import com.example.projectkpbaru.databinding.DiskusiAdapterBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChatAdapter(private val data: List<Message>) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    class ViewHolder(val binding: DiskusiAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DiskusiAdapterBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uid = Firebase.auth.currentUser!!.uid
        if (data[position].uid == uid) {
            Log.d(TAG, "onBindViewHolder: ${data[position].uid != uid}")
            with(holder.binding) {
                receivedMessageLayout.isVisible = false
                cardSent.isVisible = true
                sentMessage.text = data[position].message
            }
        } else if(data[position].uid != uid) {

            Log.d(TAG, "onBindViewHolder: ${data[position].uid != uid}")
            with(holder.binding) {
                sentMessage.isVisible = false
                receivedMessageLayout.isVisible = true
                senderName.text = data[position].userName
                receivedMessage.text = data[position].message
            }
        }
    }

    override fun getItemCount(): Int = data.size
}