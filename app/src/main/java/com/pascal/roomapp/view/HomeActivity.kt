package com.pascal.roomapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.pascal.roomapp.R
import com.pascal.roomapp.model.User
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    companion object {
        const val LOGIN_SESSION = "login_session"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var nama = intent.getStringExtra(LOGIN_SESSION)

        home_user.text = "Hi $nama"

        val navController = Navigation.findNavController(this, R.id.nav_host_home)
        NavigationUI.setupWithNavController(bottom_navigation, navController)
    }
}