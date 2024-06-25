package com.cevdetkilickeser.emerchant.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cevdetkilickeser.emerchant.data.entity.like.Like
import com.cevdetkilickeser.emerchant.databinding.LikeViewBinding

class LikesAdapter(private var context: Activity, private var likeList: List<Like>) :
    RecyclerView.Adapter<LikesAdapter.LikeViewHolder>() {
    inner class LikeViewHolder(var binding: LikeViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = LikeViewBinding.inflate(layoutInflater)
        return LikeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return likeList.size
    }

    override fun onBindViewHolder(holder: LikeViewHolder, position: Int) {
        val like = likeList[position]
        val b = holder.binding


    }
}