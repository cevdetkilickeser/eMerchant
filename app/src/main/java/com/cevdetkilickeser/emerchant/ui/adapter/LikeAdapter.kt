package com.cevdetkilickeser.emerchant.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cevdetkilickeser.emerchant.data.model.like.Like
import com.cevdetkilickeser.emerchant.databinding.LikeViewBinding

class LikeAdapter(
    private var likeList: List<Like>,
    private val onClickLikeCard: (Like) -> Unit,
    private val onClickDeleteLikeButton: (Like) -> Unit
) : RecyclerView.Adapter<LikeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LikeViewBinding.inflate(layoutInflater, parent, false)
        return LikeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return likeList.size
    }

    override fun onBindViewHolder(holder: LikeViewHolder, position: Int) {
        holder.bind(likeList[position], onClickLikeCard, onClickDeleteLikeButton)
    }
}