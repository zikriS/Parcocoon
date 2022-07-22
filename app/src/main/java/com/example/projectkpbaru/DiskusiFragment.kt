package com.example.projectkpbaru

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.projectkpbaru.databinding.FragmentDiskusiBinding
import com.google.firebase.auth.ktx.auth
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
//            val user=UserInfo()
        }
    }
}