package com.cevdetkilickeser.emerchant.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cevdetkilickeser.emerchant.databinding.FragmentLikesBinding
import com.cevdetkilickeser.emerchant.ui.adapter.LikesAdapter
import com.cevdetkilickeser.emerchant.ui.viewmodel.LikesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikesFragment : Fragment() {

    private lateinit var binding: FragmentLikesBinding
    private lateinit var viewModel: LikesViewModel
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikesBinding.inflate(layoutInflater, container, false)

        viewModel.likeList.observe(viewLifecycleOwner) { likeList ->
            val likesAdapter = LikesAdapter(requireActivity(), likeList)
            binding.rvLikes.adapter = likesAdapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: LikesViewModel by viewModels()
        viewModel = tempViewModel

        val sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        userId = sharedPref.getString("userId", "").toString()
        viewModel.getLikes(userId)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLikes(userId)
    }
}