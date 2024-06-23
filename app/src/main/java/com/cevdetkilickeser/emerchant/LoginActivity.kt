package com.cevdetkilickeser.emerchant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.cevdetkilickeser.emerchant.databinding.ActivityLoginBinding
import com.cevdetkilickeser.emerchant.ui.screen.LoginScreen
import com.cevdetkilickeser.emerchant.ui.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    LoginScreen(::onLoginClick)
                }
            }
        }

        val tempViewModel: LoginViewModel by viewModels()
        viewModel = tempViewModel

        viewModel.user.observe(this) { user ->
            user?.let {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
                finish()
            } ?: run {
                Snackbar.make(binding.root, "Login Error", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun onLoginClick(username: String, password: String) {
        viewModel.login(username, password)

        val sharedPref = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()
    }
}