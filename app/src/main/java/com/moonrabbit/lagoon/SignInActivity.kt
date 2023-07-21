package com.moonrabbit.lagoon

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.moonrabbit.lagoon.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var cardAnim:Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAnimation()

        binding.txtSignUp.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)

            val pairs : Array<android.util.Pair<View, String>> = arrayOf(
                android.util.Pair(binding.txtLogo,"logo"),
                android.util.Pair(binding.cardView,"card")
            )

            val options = ActivityOptions.makeSceneTransitionAnimation(this@SignInActivity, *pairs)
            startActivity(intent, options.toBundle())
        }
    }

    private fun setupAnimation() {
        cardAnim = AnimationUtils.loadAnimation(this, R.anim.card_animation)
        binding.cardView.startAnimation(cardAnim)
    }
}