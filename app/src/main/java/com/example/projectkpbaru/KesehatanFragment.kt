package com.example.projectkpbaru

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectkpbaru.databinding.FragmentKesehatanBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class KesehatanFragment : Fragment(R.layout.fragment_kesehatan) {
    private lateinit var binding: FragmentKesehatanBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentKesehatanBinding.bind(view)
        binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
        }
    }
}