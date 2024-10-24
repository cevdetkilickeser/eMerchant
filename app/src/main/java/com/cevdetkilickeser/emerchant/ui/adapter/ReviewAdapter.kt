package com.cevdetkilickeser.emerchant.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cevdetkilickeser.emerchant.data.model.product.Review
import com.cevdetkilickeser.emerchant.databinding.ReviewViewBinding

class ReviewAdapter(private var reviewList: List<Review>) :
    RecyclerView.Adapter<ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ReviewViewBinding.inflate(layoutInflater, parent, false)
        return ReviewViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviewList[position])
    }
}