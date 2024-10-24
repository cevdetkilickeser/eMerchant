package com.cevdetkilickeser.emerchant.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.model.like.Like
import com.cevdetkilickeser.emerchant.databinding.LikeViewBinding
import com.cevdetkilickeser.emerchant.utils.formatPrice


class LikeViewHolder(var binding: LikeViewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(like: Like, onClickLikeCard: (Like) -> Unit, onClickDeleteLikeButton: (Like) -> Unit) {

        val product = like.product
        with(binding) {
            Glide.with(this.root).load(product.thumbnail).into(this.likeViewImage)
            likeViewTitle.text = product.title
            likeViewPrice.text = formatPrice(product.price)
            likeImageViewDelete.setOnClickListener {
                onClickDeleteLikeButton(like)
            }
            likeViewCard.setOnClickListener {
                onClickLikeCard.invoke(like)
            }
        }
    }
}