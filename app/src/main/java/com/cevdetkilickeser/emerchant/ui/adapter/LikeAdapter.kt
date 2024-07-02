package com.cevdetkilickeser.emerchant.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.entity.like.Like
import com.cevdetkilickeser.emerchant.data.entity.like.UnlikeClickEvent
import com.cevdetkilickeser.emerchant.databinding.LikeViewBinding
import com.cevdetkilickeser.emerchant.ui.activity.DetailActivity
import org.greenrobot.eventbus.EventBus

class LikeAdapter(
    private var context: Activity,
    private var likeList: List<Like>
) : RecyclerView.Adapter<LikeAdapter.LikeViewHolder>() {

    inner class LikeViewHolder(var binding: LikeViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = LikeViewBinding.inflate(layoutInflater, parent, false)
        return LikeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return likeList.size
    }

    override fun onBindViewHolder(holder: LikeViewHolder, position: Int) {
        val like = likeList[position]
        val product = like.product

        holder.binding.apply {
            Glide.with(this.root).load(product.thumbnail).into(this.likeViewImage)
            likeViewTitle.text = product.title
            likeViewPrice.text = buildString {
                append("$ ")
                append(product.price)
            }
            likeImageViewDelete.setOnClickListener {
                EventBus.getDefault().post(UnlikeClickEvent(like))
            }
            likeViewCard.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("product", like.product)
                context.startActivity(intent)
            }
        }
    }
}