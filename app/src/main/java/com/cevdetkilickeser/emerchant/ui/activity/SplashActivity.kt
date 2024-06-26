package com.cevdetkilickeser.emerchant.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cevdetkilickeser.emerchant.databinding.ActivitySplashBinding
import com.cevdetkilickeser.emerchant.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageViewIcon.alpha = 0f

        val tempViewModel: LoginViewModel by viewModels()
        viewModel = tempViewModel

        val sharedPref = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "").toString()
        val password = sharedPref.getString("password", "").toString()

        viewModel.login(username, password)

        viewModel.user.observe(this) { user ->
            user?.let {
                val sharedPreferences =
                    this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("token", it.token)
                editor.putString("userId", user.id.toString())
                editor.apply()

                binding.imageViewIcon.animate().setDuration(1500).alpha(1f).withEndAction {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    intent.putExtra("user", it)
                    startActivity(intent)
                    finish()
                }

            } ?: run {
                binding.imageViewIcon.animate().setDuration(1500).alpha(1f).withEndAction {
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}