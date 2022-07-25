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
    private lateinit var binding: FragmentDiskusiBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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

        val msgLIst: MutableList<Message> = mutableListOf()
        val adapter = ChatAdapter(msgLIst)
        binding.rvChat.adapter = adapter
        binding.rvChat.layoutManager = LinearLayoutManager(requireContext())




        getMessage(adapter, msgLIst)
    }

    private fun getMessage(adapter: ChatAdapter, msgLIst: MutableList<Message>) {
        Firebase.database.getReference("chatData")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        if (msgLIst.isEmpty()) {
                            for (dataSnapshot in snapshot.children) {
                                msgLIst.add(
                                    Message(
                                        dataSnapshot.child("uid").value.toString(),
                                        dataSnapshot.child("userName").value.toString(),
                                        dataSnapshot.child("message").value.toString()
                                    )
                                )
                                adapter.notifyItemInserted(msgLIst.size - 1)
                            }
                        } else {
                            with(snapshot.children.last()) {
                                msgLIst.add(
                                    Message(
                                        snapshot.child("uid").value as String,
                                        snapshot.child("userName").value as String,
                                        snapshot.child("message").value as String
                                    )
                                )
                                adapter.notifyItemInserted(msgLIst.size - 1)
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, "onCancelled: Cancelled Listener")
                }
            })
    }

    private fun sendMessage(uid: String, username: String, message: String) {
        val msg = Message(
            uid = uid,
            userName = username,
            message = message
        )
        Firebase.database.getReference("chatData").push().setValue(msg)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(requireContext(), "Pesan gagal dikirim", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }
}