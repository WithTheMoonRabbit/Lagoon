package com.moonrabbit.lagoon

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView

class SplashActivity : AppCompatActivity() {

    private var SPLASH_SCREEN : Int = 1500

    private lateinit var topAnim: Animation
    private lateinit var logo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)

        logo = findViewById(R.id.textView)

        logo.animation = topAnim


        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, SignInActivity::class.java)

            val p = android.util.Pair<View, String>(logo, "logo")

            val options : ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this@SplashActivity, p)
            startActivity(intent,options.toBundle())
            finish()
        }, SPLASH_SCREEN.toLong())

    }
}