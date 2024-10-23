package com.cevdetkilickeser.emerchant.ui.activity

import android.content.Context
import android.content.SharedPreferences
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
import com.cevdetkilickeser.emerchant.utils.parcelable
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var like: Like
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.parcelable<Product>("product")!!
        sharedPref = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("userId", "").toString()

        viewModel.checkLike(userId, product.id)

        viewModel.like.observe(this) { likeProduct ->
            likeProduct?.let {
                binding.detailPageCheckbox.isChecked = true
                this.like = it

            } ?: run {
                binding.detailPageCheckbox.isChecked = false
                this.like = Like(0, userId, product)
            }
        }

        setViewValues(product)

        viewModel.isAdded.observe(this) { isAdded ->
            showSnackBar(product, isAdded)
        }

        binding.detailPageBackButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.detailPageCheckbox.setOnClickListener {
            onClickCheckBox()
        }

        binding.detailPageReviews.setOnClickListener {
            val reviews = product.reviews
            showReviewsBottomSheet(reviews)
        }

        binding.detailPageAddToCartButton.setOnClickListener {
            viewModel.onClickAddToCart(userId.toInt(), product.id)
        }
    }

    private fun setViewValues(product: Product) {
        with(binding) {
            Glide.with(this.root).load(product.thumbnail).into(this.detilPageImage)
            detailPageTitle.text = product.title
            detailPageDescription.text = product.description
            detailPageRating.text = product.rating.toString()
            detailPageBrand.text = product.brand
            detailPageWarranty.text = product.warrantyInformation
            detailPageShipping.text = product.shippingInformation
            detailPageAvailibility.text = product.availabilityStatus
            detailPageReturn.text = product.returnPolicy
            detailPageWeight.text = product.weight.toString()
            detailPageWidth.text = product.dimensions.width.toString()
            detailPageHeight.text = product.dimensions.height.toString()
            detailPageDepth.text = product.dimensions.depth.toString()
            detailPagePrice.text = "$ " + product.price.toString()
        }
    }

    private fun showReviewsBottomSheet(reviews: List<Review>) {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(R.layout.layout_bottom_sheet_reviews, null)
        bottomSheetDialog.setContentView(bottomSheetView)

        val recyclerViewReviews =
            bottomSheetView.findViewById<RecyclerView>(R.id.recyclerViewReviews)
        recyclerViewReviews.adapter = ReviewAdapter(reviews)

        bottomSheetDialog.show()
    }

    private fun showSnackBar(product: Product, isAdded: Boolean) {
        if (isAdded) {
            Snackbar.make(
                binding.root,
                "${product.title} has been successfully added to the cart",
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            Snackbar.make(
                binding.root,
                "An error occurred. Please try again",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun onClickCheckBox() {
        if (binding.detailPageCheckbox.isChecked) {
            viewModel.insertLike(like)
        } else {
            viewModel.deleteLike(like)
        }
    }
}