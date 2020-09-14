package com.pascal.roomapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.pascal.roomapp.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this, MainActivity::class.java))
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}