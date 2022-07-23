package com.example.projectkpbaru.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkpbaru.RVDataclass
import com.example.projectkpbaru.databinding.RvFoodBinding
import com.example.projectkpbaru.databinding.RvSehatBinding

class RvSehatAdapter(private val list: List<RVDataclass>) :
    RecyclerView.Adapter<RvSehatAdapter.ViewHolder>() {
    class ViewHolder(
        val binding: RvSehatBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvSehatBinding.inflate(layoutInflater)
        return RvSehatAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            tvHeading.text = list[position].TitlePram
            tvDesc.text = list[position].DescPram
            titleImage.setImageResource(list[position].imagePram)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
}