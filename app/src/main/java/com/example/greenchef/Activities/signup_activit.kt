package com.example.greenchef

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)

        val btnTabSignIn = findViewById<Button>(R.id.btnTabSignIn)

        // Set click listeners
        btnTabSignIn.setOnClickListener {
            val loginIntent = Intent(this, SignInActivity::class.java)
            startActivity(loginIntent)
        }
    }
}