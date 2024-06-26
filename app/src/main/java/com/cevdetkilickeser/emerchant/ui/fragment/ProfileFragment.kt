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
import com.cevdetkilickeser.emerchant.data.entity.profile.Hair
import com.cevdetkilickeser.emerchant.data.entity.profile.Profile
import com.cevdetkilickeser.emerchant.data.entity.profile.UpdateProfileRequest
import com.cevdetkilickeser.emerchant.databinding.FragmentProfileBinding
import com.cevdetkilickeser.emerchant.ui.viewmodel.ProfileViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        val sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("userId", "").toString()

        viewModel.profile.observe(viewLifecycleOwner) { profile ->
            loadProfileInfo(profile)
        }

        viewModel.updatedProfile.observe(viewLifecycleOwner) {
            loadProfileInfo(it)
        }

        binding.linearLayoutPersonalInfo.setOnClickListener {
            if (binding.profilePagePersInfoDetail.visibility == View.VISIBLE) {
                binding.profilePagePersInfoDetail.visibility = View.GONE
                binding.personalInfoArrow.setImageResource(R.drawable.ic_arrow_down)
            } else {
                binding.profilePagePersInfoDetail.visibility = View.VISIBLE
                binding.personalInfoArrow.setImageResource(R.drawable.ic_arrow_up)
            }
        }

        binding.buttonSaveChanges.setOnClickListener {
            var updateProfileRequest: UpdateProfileRequest
            binding.apply {
                val firstName = firstName.text.toString()
                val lastName = lastName.text.toString()
                val age = age.text.toString().toInt()
                val gender = gender.text.toString()
                val birthDt = birthDt.text.toString()
                val blood = blood.text.toString()
                val height = height.text.toString().toDouble()
                val weight = weight.text.toString().toDouble()
                val eyeColor = eyeColor.text.toString()
                val hairColor = hairColor.text.toString()
                val hairType = hairType.text.toString()
                val email = email.text.toString()
                val phone = phone.text.toString()
                updateProfileRequest = UpdateProfileRequest(
                    age,
                    birthDt,
                    blood,
                    email,
                    eyeColor,
                    firstName,
                    gender,
                    Hair(hairColor, hairType),
                    height,
                    lastName,
                    phone,
                    weight
                )
            }
            viewModel.updateProfile(userId, updateProfileRequest)
            Snackbar.make(binding.root, "Profile updated.", Snackbar.LENGTH_SHORT).show()
        }

        binding.linearLayoutContactInfo.setOnClickListener {
            if (binding.profilePageContactInfoDetail.visibility == View.VISIBLE) {
                binding.profilePageContactInfoDetail.visibility = View.GONE
                binding.contactInfoArrow.setImageResource(R.drawable.ic_arrow_down)
            } else {
                binding.profilePageContactInfoDetail.visibility = View.VISIBLE
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
        binding.apply {
            Glide.with(this.root).load(profile.image).into(this.profilePageImage)
            firstName.setText(profile.firstName)
            lastName.setText(profile.lastName)
            age.setText(profile.age.toString())
            gender.setText(profile.gender)
            birthDt.setText(profile.birthDate)
            blood.setText(profile.bloodGroup)
            height.setText(profile.height.toString())
            weight.setText(profile.weight.toString())
            eyeColor.setText(profile.eyeColor)
            hairColor.setText(profile.hair.color)
            hairType.setText(profile.hair.type)
            email.setText(profile.email)
            phone.setText(profile.phone)
        }
    }
}