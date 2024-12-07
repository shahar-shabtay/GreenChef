package com.example.greenchef

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_activity)

        val btnTabSignUp = findViewById<Button>(R.id.btnTabSignUp)

        // Set click listeners
        btnTabSignUp.setOnClickListener {
            val loginIntent = Intent(this, SignUpActivity::class.java)
            startActivity(loginIntent)
        }
    }
}