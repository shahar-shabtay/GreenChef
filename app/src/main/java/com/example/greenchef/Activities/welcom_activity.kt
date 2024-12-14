package com.example.greenchef

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity)

        // Find buttons by ID
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        // Set click listeners
        btnSignIn.setOnClickListener {
            Log.d("WelcomeActivity", "Sign In button clicked") // Debug message
            val loginIntent = Intent(this, SignInActivity::class.java)
            startActivity(loginIntent)

            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        btnSignUp.setOnClickListener {
            Log.d("WelcomeActivity", "Sign Up button clicked") // Debug message
            val signupIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signupIntent)

            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}