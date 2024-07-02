package com.cevdetkilickeser.emerchant.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cevdetkilickeser.emerchant.data.entity.product.Review
import com.cevdetkilickeser.emerchant.databinding.ReviewViewBinding
import com.cevdetkilickeser.emerchant.utils.DateConverter

class ReviewAdapter(private val context: Context, private var reviewList: List<Review>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewsViewHolder>() {
    inner class ReviewsViewHolder(val binding: ReviewViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ReviewViewBinding.inflate(layoutInflater, parent, false)
        return ReviewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        val review = reviewList[position]

        holder.binding.apply {
            reviewerName.text = review.reviewerName
            reviewerRate.text = review.rating.toString()
            reviewerComent.text = review.comment
            reviewDate.text = DateConverter.zoneDateConverter(review.date)
        }
    }
}