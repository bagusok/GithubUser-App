package com.bagusok.github.ui.main.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bagusok.github.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashTime: Long = 1500

        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, splashTime)

    }
}