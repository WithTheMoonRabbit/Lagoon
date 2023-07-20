package com.moonrabbit.lagoon

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.moonrabbit.lagoon.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var topAnim: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAnimation()
        Handler().postDelayed({
            startSignInActivity()
        }, 1500)
    }

    private fun setupAnimation() {
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        binding.txtLogo.startAnimation(topAnim)
    }

    private fun startSignInActivity() {
        val intent = Intent(this@SplashActivity, SignInActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(this@SplashActivity, binding.txtLogo, "logo")
        startActivity(intent, options.toBundle())
        finish()
    }
}
