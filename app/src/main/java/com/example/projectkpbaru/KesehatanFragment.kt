package com.example.projectkpbaru

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectkpbaru.adapter.RvSehatAdapter
import com.example.projectkpbaru.databinding.FragmentKesehatanBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class KesehatanFragment : Fragment(R.layout.fragment_kesehatan) {
    private lateinit var binding : FragmentKesehatanBinding
    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentKesehatanBinding.bind(view)

        val title = resources.getStringArray(R.array.kesehatan_title)
        val desc = resources.getStringArray(R.array.DescSehat)
        val imageArry = arrayOf(
            R.drawable.grooming
        )

        val data = mutableListOf<RVDataclass>()
        for (i in imageArry.indices) {
            val result = RVDataclass(imageArry[i], title[i], desc[i])
            data.add(result)
            Log.d(ContentValues.TAG, "Data : $result")
        }

        val adapter = RvSehatAdapter(data,requireContext())
        binding.RVKesehatan.adapter = adapter
        binding.RVKesehatan.layoutManager = LinearLayoutManager(requireContext())

        binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
        }
    }
}