package com.cevdetkilickeser.emerchant.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.R
import com.cevdetkilickeser.emerchant.data.entity.like.Like
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.data.entity.product.Review
import com.cevdetkilickeser.emerchant.databinding.ActivityDetailBinding
import com.cevdetkilickeser.emerchant.ui.adapter.ReviewAdapter
import com.cevdetkilickeser.emerchant.ui.viewmodel.DetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
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
        val userId = sharedPref.getString("userId", "").toString()

        viewModel.checkLike(userId, product.id)

        viewModel.like.observe(this) { likeProduct ->
            likeProduct?.let {
                binding.detailPageCheckbox.isChecked = true
                this.like = it

            } ?: run {
                binding.detailPageCheckbox.isChecked = false
                if (product.brand != null) {
                    this.like = Like(0, userId, product)
                } else {
                    val defaultProduct = product.copy(brand = "Unknown")
                    this.like = Like(0, userId, defaultProduct)
                }
            }
        }

        viewModel.addCartId.observe(this) {
            Snackbar.make(
                binding.root,
                "${product.title} added to cart.\n(Cart Id: $it)",
                Snackbar.LENGTH_SHORT
            ).show()
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

        binding.detailPageReviews.setOnClickListener {
            val reviews = product.reviews
            showReviewsBottomSheet(reviews)
        }

        binding.detailPageAddToCartButton.setOnClickListener {
            viewModel.addCart(userId, listOf(product))
        }

        product.let {
            binding.apply {
                Glide.with(this.root).load(it.thumbnail).into(this.detilPageImage)
                detailPageTitle.text = it.title
                detailPageDescription.text = it.description
                detailPageRating.text = it.rating.toString()
                detailPageBrand.text = it.brand
                detailPageWarranty.text = it.warrantyInformation
                detailPageShipping.text = it.shippingInformation
                detailPageAvailibility.text = it.availabilityStatus
                detailPageReturn.text = it.returnPolicy
                detailPageWeight.text = it.weight.toString()
                detailPageWidth.text = it.dimensions.width.toString()
                detailPageHeight.text = it.dimensions.height.toString()
                detailPageDepth.text = it.dimensions.depth.toString()
                detailPagePrice.text = "$ " + it.price.toString()
            }
        }
    }

    private fun showReviewsBottomSheet(reviews: List<Review>) {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(R.layout.layout_bottom_sheet_reviews, null)
        bottomSheetDialog.setContentView(bottomSheetView)

        val recyclerViewReviews =
            bottomSheetView.findViewById<RecyclerView>(R.id.recyclerViewReviews)
        recyclerViewReviews.adapter = ReviewAdapter(this, reviews)

        bottomSheetDialog.show()
    }
}