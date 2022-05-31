package com.example.bot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val admin = LogInAdmin()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.adminLayout,admin)
            commit()}
    }
}