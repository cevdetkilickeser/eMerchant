package com.cevdetkilickeser.emerchant.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.R
import com.cevdetkilickeser.emerchant.data.entity.profile.Profile
import com.cevdetkilickeser.emerchant.databinding.FragmentProfileBinding
import com.cevdetkilickeser.emerchant.ui.viewmodel.ProfileViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        val sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("userId", "").toString()

        viewModel.profile.observe(viewLifecycleOwner) { profile ->
            loadProfileInfo(profile)
        }

        viewModel.updateProfileId.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, "Profile updated.\n(User Id: $it)", Snackbar.LENGTH_SHORT)
                .show()
        }

        binding.linearLayoutPersonalInfo.setOnClickListener {
            if (binding.profilePagePersInfoDetail.visibility == View.VISIBLE) {
                binding.profilePagePersInfoDetail.visibility = View.GONE
                binding.buttonSavePersInfo.visibility = View.GONE
                binding.personalInfoArrow.setImageResource(R.drawable.ic_arrow_down)
            } else {
                binding.profilePagePersInfoDetail.visibility = View.VISIBLE
                binding.buttonSavePersInfo.visibility = View.VISIBLE
                binding.personalInfoArrow.setImageResource(R.drawable.ic_arrow_up)
            }
        }

        binding.buttonSavePersInfo.setOnClickListener {
            //val lastName = binding.lastName.text.toString()
            //viewModel.updateProfile(userId, lastName)
            Snackbar.make(binding.root, "Profile updated.", Snackbar.LENGTH_SHORT)
                .show()
        }

        binding.linearLayoutContactInfo.setOnClickListener {
            if (binding.profilePageContactInfoDetail.visibility == View.VISIBLE) {
                binding.profilePageContactInfoDetail.visibility = View.GONE
                binding.buttonSaveContactInfo.visibility = View.GONE
                binding.contactInfoArrow.setImageResource(R.drawable.ic_arrow_down)
            } else {
                binding.profilePageContactInfoDetail.visibility = View.VISIBLE
                binding.buttonSaveContactInfo.visibility = View.VISIBLE
                binding.contactInfoArrow.setImageResource(R.drawable.ic_arrow_up)
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: ProfileViewModel by viewModels()
        viewModel = tempViewModel

        val sharedPref: SharedPreferences =
            requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", "").toString()
        viewModel.getAuthUserProfile(token)
    }

    private fun loadProfileInfo(profile: Profile) {
        Glide.with(binding.root).load(profile.image).into(binding.profilePageImage)
        binding.firstName.setText(profile.firstName)
        binding.lastName.setText(profile.lastName)
        binding.age.setText(profile.age.toString())
        binding.gender.setText(profile.gender)
        binding.birthDt.setText(profile.birthDate)
        binding.blood.setText(profile.bloodGroup)
        binding.height.setText(profile.height.toString())
        binding.weight.setText(profile.weight.toString())
        binding.eyeColor.setText(profile.eyeColor)
        binding.hairColor.setText(profile.hair.color)
        binding.hairType.setText(profile.hair.type)

        binding.email.setText(profile.email)
        binding.phone.setText(profile.phone)
    }
}