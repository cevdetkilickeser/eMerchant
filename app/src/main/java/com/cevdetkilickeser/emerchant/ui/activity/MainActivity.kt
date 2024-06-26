package com.cevdetkilickeser.emerchant.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.cevdetkilickeser.emerchant.R
import com.cevdetkilickeser.emerchant.data.entity.user.User
import com.cevdetkilickeser.emerchant.databinding.ActivityMainBinding
import com.cevdetkilickeser.emerchant.databinding.DrawerHeaderBinding
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var headerBinding: DrawerHeaderBinding
    private lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.navigationView, navHostFragment.navController)

        val user = intent.getSerializableExtra("user") as User

        binding.buttonLogout.setOnClickListener {
            val sharedPref = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            sharedPref.edit().clear().apply()

            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            0,
            0
        )
        val drawerArrowDrawable = DrawerArrowDrawable(this)
        drawerArrowDrawable.color = ContextCompat.getColor(this, R.color.white)
        toggle.drawerArrowDrawable = drawerArrowDrawable
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val headerView = binding.navigationView.getHeaderView(0)
        headerBinding = DrawerHeaderBinding.bind(headerView)
        headerBinding.apply {
            headerName.text = user.firstName
            headerLastname.text = user.lastName
            Glide.with(this.root).load(user.image).into(this.headerImage)
        }

        onClickBackPress()

        setUpRemoteConfig()

        listenConfigUpdates()
    }

    private fun onClickBackPress() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    private fun setUpRemoteConfig() {
        remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
    }

    private fun fetchRemoteConfig() {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val bgColor = remoteConfig.getString("background_color")
                    updateBackgroundColor(bgColor)
                } else {
                    Log.e("Remote Config", task.exception!!.message.toString())
                }
            }
    }

    private fun updateBackgroundColor(colorString: String) {
        try {
            val color =
                if (colorString.isEmpty()) Color.TRANSPARENT else Color.parseColor(colorString)
            binding.navHostFragment.setBackgroundColor(color)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            binding.navHostFragment.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    private fun listenConfigUpdates() {
        remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                Log.d("Remote Config", "Updated keys: " + configUpdate.updatedKeys)
                fetchRemoteConfig()
            }

            override fun onError(error: FirebaseRemoteConfigException) {
                Log.w("Remote Config", "Config update error with code: " + error.code, error)
            }
        })
    }
}