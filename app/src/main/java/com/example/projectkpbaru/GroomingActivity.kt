package com.example.projectkpbaru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectkpbaru.databinding.ActivityInfoGroomingBinding

class GroomingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInfoGroomingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoGroomingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val txt = intent.getStringExtra("description")
        val img = intent.getIntExtra("banner", Int.MAX_VALUE)
        binding.tvItemDescrption.text = txt
        binding.RVGrooming.setImageResource(img)
    }
}