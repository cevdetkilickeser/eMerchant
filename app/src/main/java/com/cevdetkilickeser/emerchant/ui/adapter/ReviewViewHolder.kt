package com.cevdetkilickeser.emerchant.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.cevdetkilickeser.emerchant.data.entity.product.Review
import com.cevdetkilickeser.emerchant.databinding.ReviewViewBinding
import com.cevdetkilickeser.emerchant.utils.DateConverter

class ReviewViewHolder(val binding: ReviewViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(review: Review) {
        binding.apply {
            reviewerName.text = review.reviewerName
            reviewerRate.text = review.rating.toString()
            reviewerComent.text = review.comment
            reviewDate.text = DateConverter.zoneDateConverter(review.date)
        }
    }
}