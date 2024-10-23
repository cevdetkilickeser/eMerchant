package com.cevdetkilickeser.emerchant.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.data.entity.profile.Address
import com.cevdetkilickeser.emerchant.data.entity.profile.Profile
import com.cevdetkilickeser.emerchant.data.entity.profile.UpdateProfileRequest
import com.cevdetkilickeser.emerchant.databinding.FragmentProfileBinding
import com.cevdetkilickeser.emerchant.ui.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var sharedPref: SharedPreferences
    private lateinit var profile: Profile
    var userId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        viewModel.isProgress.observe(viewLifecycleOwner) { isProgress ->
            if (isProgress) {
                binding.progressBarProfile.visibility = View.VISIBLE
            } else {
                binding.progressBarProfile.visibility = View.GONE
            }
        }

        viewModel.profile.observe(viewLifecycleOwner) { profile ->
            this.profile = profile
            binding.apply {
                Glide.with(this.root).load(profile.image).into(this.imageViewProfile)
                firstname.text = profile.firstName
                lastname.text = profile.lastName
                age.text = profile.age.toString()
                gender.text = profile.gender
                birthdate.text = profile.birthDate
                email.text = profile.email
                phone.text = profile.phone
                address.text = profile.address.address
                city.text = profile.address.city
                state.text = profile.address.state
                country.text = profile.address.country
            }

        }

        binding.updateProfile.setOnClickListener {
            with(binding) {
                profileInfoLayout.visibility = View.GONE
                profileUpdateLayout.visibility = View.VISIBLE
                firstnameNew.setText(profile.firstName)
                lastnameNew.setText(profile.lastName)
                ageNew.setText(profile.age.toString())
                birthdateNew.setText(profile.birthDate)
                genderNew.setText(profile.gender)
                emailNew.setText(profile.email)
                phoneNew.setText(profile.phone)
                addressNew.setText(profile.address.address)
                cityNew.setText(profile.address.city)
                stateNew.setText(profile.address.state)
                countryNew.setText(profile.address.country)
            }
        }

        binding.buttonSaveChanges.setOnClickListener {
            val firstname = binding.firstnameNew.text.toString()
            val lastName = binding.lastnameNew.text.toString()
            val age = binding.ageNew.text.toString().toInt()
            val birthDate = binding.genderNew.text.toString()
            val gender = binding.birthdateNew.text.toString()
            val email = binding.emailNew.text.toString()
            val phone = binding.phoneNew.text.toString()
            val address = binding.addressNew.text.toString()
            val city = binding.cityNew.text.toString()
            val state = binding.stateNew.text.toString()
            val country = binding.countryNew.text.toString()
            val updateProfileRequest = UpdateProfileRequest(
                firstname,
                lastName,
                age,
                gender,
                birthDate,
                email,
                phone,
                Address(address, city, null, country, null, state)
            )
            viewModel.updateProfile(userId, updateProfileRequest)
            binding.profileUpdateLayout.visibility = View.GONE
            binding.profileInfoLayout.visibility = View.VISIBLE
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: ProfileViewModel by viewModels()
        viewModel = tempViewModel

        sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        userId = sharedPref.getString("userId", "").toString().toInt()
        val token = sharedPref.getString("token", "").toString()
        viewModel.getAuthUserProfile(token)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                if (binding.profileUpdateLayout.visibility == View.VISIBLE) {
                    binding.profileUpdateLayout.visibility = View.GONE
                    binding.profileInfoLayout.visibility = View.VISIBLE
                } else {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }
}