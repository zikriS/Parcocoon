package com.example.projectkpbaru.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkpbaru.RVDataclass
import com.example.projectkpbaru.databinding.RvFoodBinding

class RvFoodAdapter(private val list : List<RVDataclass>) :
    RecyclerView.Adapter<RvFoodAdapter.ViewHolder>() {

    class ViewHolder(val binding : RvFoodBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvFoodBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int) {
        with(holder.binding) {
            tvHeading.text = list[position].TitlePram
            tvDesc.text = list[position].DescPram
            titleImage.setImageResource(list[position].imagePram)
        }
    }

    override fun getItemCount() : Int = list.size
}