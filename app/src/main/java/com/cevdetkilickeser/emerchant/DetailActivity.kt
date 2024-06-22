package com.cevdetkilickeser.emerchant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.getSerializableExtra("product") as Product

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