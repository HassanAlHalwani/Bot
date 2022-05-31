package com.example.bot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class Smurfs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smurfs)

        val login = LogIn()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.Activity_Smurfs_Layout,login)
            commit()
        }
    }
}