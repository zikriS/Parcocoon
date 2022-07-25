package com.example.projectkpbaru

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectkpbaru.adapter.ChatAdapter
import com.example.projectkpbaru.databinding.FragmentDiskusiBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DiskusiFragment : Fragment(R.layout.fragment_diskusi) {
    private lateinit var binding : FragmentDiskusiBinding
    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDiskusiBinding.bind(view)
        binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
        }

        binding.ImageViewSend.setOnClickListener {
            sendMessage(
                Firebase.auth.currentUser!!.uid,
                requireActivity().getSharedPreferences("userProfile", Context.MODE_PRIVATE)
                    .getString("username", "")!!,
                binding.TvInputPesan.text.toString()
            )
        }

        val msgLIst : MutableList<Message> = mutableListOf()
        val adapter = ChatAdapter(msgLIst)
        binding.rvChat.adapter = adapter
        binding.rvChat.layoutManager = LinearLayoutManager(requireContext())




        getMessage(adapter, msgLIst)
    }


    private fun getMessage(adapter : ChatAdapter, msgLIst : MutableList<Message>) {
        Firebase.database.getReference("chatData")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot : DataSnapshot) {
                    if (snapshot.exists()) {
                        if (msgLIst.isEmpty()) {
                            for (data in snapshot.children) {
                                msgLIst.add(
                                    Message(
                                        data.child("uid").value as String,
                                        data.child("userName").value as String,
                                        data.child("message").value as String
                                    )
                                )
                                adapter.notifyItemInserted(msgLIst.size - 1)
                            }
                        } else {
                            with(snapshot.children.last()) {
                                msgLIst.add(
                                    Message(
                                        this.child("uid").value as String,
                                        this.child("userName").value as String,
                                        this.child("message").value as String
                                    )
                                )
                                adapter.notifyItemInserted(msgLIst.size - 1)
                            }
                        }
                    }
                }

                override fun onCancelled(error : DatabaseError) {
                    Log.w(TAG, "onCancelled: Error")
                }
            })
    }

    private fun sendMessage(uid : String, username : String, message : String) {
        val msg = Message(
            uid = uid,
            userName = username,
            message = message
        )
        binding.TvInputPesan.text.clear()
        Firebase.database.getReference("chatData").push().setValue(msg).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(requireContext(), "Pesan Berhasil dikirim", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Pesan Gagal dikirim", Toast.LENGTH_SHORT).show()
            }
        }
    }
}