package com.cevdetkilickeser.emerchant.ui.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cevdetkilickeser.emerchant.data.model.like.Like
import com.cevdetkilickeser.emerchant.databinding.FragmentLikesBinding
import com.cevdetkilickeser.emerchant.ui.activity.DetailActivity
import com.cevdetkilickeser.emerchant.ui.adapter.LikeAdapter
import com.cevdetkilickeser.emerchant.ui.viewmodel.LikesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikesFragment : Fragment() {

    private lateinit var binding: FragmentLikesBinding
    private val viewModel: LikesViewModel by viewModels()
    private lateinit var sharedPref: SharedPreferences
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLikesBinding.inflate(layoutInflater, container, false)

        viewModel.likeList.observe(viewLifecycleOwner) { likeList ->
            val likeAdapter = LikeAdapter(likeList, ::onClickLikeCard, ::onClickDeleteLikeButton)
            binding.rvLikes.adapter = likeAdapter
            showEmptyLikes(likeList)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        userId = sharedPref.getString("userId", "").toString()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLikes(userId)
    }

    private fun onClickLikeCard(like: Like) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("product", like.product)
        startActivity(intent)
    }

    private fun onClickDeleteLikeButton(like: Like) {
        viewModel.deleteLike(like)
    }

    private fun showEmptyLikes(likeList: List<Like>) {
        if (likeList.isEmpty()) {
            binding.imageViewEmptyLikes.visibility = View.VISIBLE
        } else {
            binding.imageViewEmptyLikes.visibility = View.INVISIBLE
        }
    }
}