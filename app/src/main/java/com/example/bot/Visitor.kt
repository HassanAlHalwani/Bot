package com.example.bot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Visitor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitor)

        val Questions_List_2 = QuestionsList2()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.Activity_Visitor_Layout,Questions_List_2)
            commit()
        }
    }
}