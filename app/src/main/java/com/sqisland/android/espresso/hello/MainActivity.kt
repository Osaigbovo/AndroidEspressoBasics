package com.sqisland.android.espresso.hello

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var greetButton: Button
    private lateinit var greetingView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle(R.string.title) // Title is not a part of ContentView and no ID for it.

        greetButton = findViewById(R.id.greet_button)
        greetingView = findViewById(R.id.greeting)
    }

    fun greet(v: View) {
        greetButton.isEnabled = false
        greetingView.setText(R.string.hello)
    }

}