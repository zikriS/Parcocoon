package com.example.projectkpbaru

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectkpbaru.adapter.RvSehatAdapter
import com.example.projectkpbaru.databinding.ActivityInfoGroomingBinding
import com.example.projectkpbaru.databinding.FragmentFoodBinding
import com.example.projectkpbaru.databinding.FragmentKesehatanBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class InfoGrooming : Fragment(R.layout.activity_info_grooming) {
    private lateinit var binding: ActivityInfoGroomingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActivityInfoGroomingBinding.bind(view)

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
    }
}





