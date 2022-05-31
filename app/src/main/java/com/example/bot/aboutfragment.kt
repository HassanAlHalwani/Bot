package com.example.bot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class aboutfragment: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var aboutView : View = inflater.inflate(R.layout.about_fragment, container, false)
        aboutView.findViewById<Button>(R.id.aboutbtn).setOnClickListener {
            dismiss()
        }
        return aboutView
    }

}