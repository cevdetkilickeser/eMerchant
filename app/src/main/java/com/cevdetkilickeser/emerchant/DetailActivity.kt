package com.cevdetkilickeser.emerchant

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.entity.like.Like
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.databinding.ActivityDetailBinding
import com.cevdetkilickeser.emerchant.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var like: Like

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tempViewModel: DetailViewModel by viewModels()
        viewModel = tempViewModel

        val product = intent.getSerializableExtra("product") as Product
        val sharedPref = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("userId", "")

        viewModel.checkLike(userId!!, product.id.toString())

        viewModel.like.observe(this) { likeProduct ->
            likeProduct?.let {
                binding.detailPageCheckbox.isChecked = true
                like = it
            } ?: run {
                binding.detailPageCheckbox.isChecked = false
                like = Like(0, userId, product.id.toString())
            }
        }

        binding.detailPageBackButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.detailPageCheckbox.setOnClickListener {
            if (binding.detailPageCheckbox.isChecked) {
                viewModel.insertLike(like)
            } else {
                viewModel.deleteLike(like)
            }
        }
        product.let {
            Glide.with(binding.root).load(it.thumbnail).into(binding.detilPageImage)
            binding.detailPageTitle.text = it.title
            binding.detailPageDescription.text = it.description
            binding.detailPageRating.text = it.rating.toString()
            binding.detailPageBrand.text = it.brand
            binding.detailPageWarranty.text = it.warrantyInformation
            binding.detailPageShipping.text = it.shippingInformation
            binding.detailPageAvailibility.text = it.availabilityStatus
            binding.detailPageReturn.text = it.returnPolicy
            binding.detailPageWeight.text = it.weight.toString()
            binding.detailPageWidth.text = it.dimensions.width.toString()
            binding.detailPageHeight.text = it.dimensions.height.toString()
            binding.detailPageDepth.text = it.dimensions.depth.toString()
            binding.detailPagePrice.text = "$ " + it.price.toString()
        }
    }
}