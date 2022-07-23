package com.example.projectkpbaru.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectkpbaru.RVDataclass
import com.example.projectkpbaru.databinding.RvSehatBinding

class RvSehatAdapter(private val list: List<RVDataclass>, private val mContext:Context) :
    RecyclerView.Adapter<RvSehatAdapter.ViewHolder>() {

    class ViewHolder(val binding: RvSehatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvSehatBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            tvHeading.text = list[position].TitlePram
            tvDesc.text = list[position].DescPram
            Glide.with(mContext).load(list[position].imagePram).fitCenter().into(titleImage)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
}