package com.cevdetkilickeser.emerchant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cevdetkilickeser.emerchant.databinding.ActivitySplashScreenBinding
import com.cevdetkilickeser.emerchant.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tempViewModel: LoginViewModel by viewModels()
        viewModel = tempViewModel

        val sharedPref = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "")
        val password = sharedPref.getString("password", "")

        viewModel.login(username!!, password!!)

        viewModel.user.observe(this) { user ->
            user?.let {
                val sharedPreferences =
                    this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("token", it.token)
                editor.putString("userId", user.id.toString())
                editor.apply()

                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                intent.putExtra("user", it)
                startActivity(intent)
                finish()

            } ?: run {
                val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}