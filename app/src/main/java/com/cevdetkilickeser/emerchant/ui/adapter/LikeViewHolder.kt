package com.cevdetkilickeser.emerchant.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.entity.like.Like
import com.cevdetkilickeser.emerchant.databinding.LikeViewBinding


class LikeViewHolder(var binding: LikeViewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(like: Like, onClickLikeCard: (Like) -> Unit, onClickDeleteLikeButton: (Like) -> Unit) {

        val product = like.product
        binding.apply {
            Glide.with(this.root).load(product.thumbnail).into(this.likeViewImage)
            likeViewTitle.text = product.title
            likeViewPrice.text = buildString {
                append("$ ")
                append(product.price)
            }
            likeImageViewDelete.setOnClickListener {
                onClickDeleteLikeButton(like)
            }
            likeViewCard.setOnClickListener {
                onClickLikeCard.invoke(like)
            }
        }
    }
}