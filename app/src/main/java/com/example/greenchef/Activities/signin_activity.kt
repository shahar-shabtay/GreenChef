package com.example.greenchef

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.greenchef.Database.AppDatabase

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_activity)


        // Navigation
        val btnTabSignUp = findViewById<Button>(R.id.btnTabSignUp)
        val btnWelcome = findViewById<ImageView>(R.id.btnBackArrow)

        // Set click listener with animation
        btnTabSignUp.setOnClickListener {
            val signupIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signupIntent)

            // Apply custom animation for activity transition
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        btnWelcome.setOnClickListener {
            val welcomeIntent = Intent(this, WelcomeActivity::class.java)
            startActivity(welcomeIntent)

            // Apply custom animation for activity transition
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        // SignIn
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)

        btnSignIn.setOnClickListener {
            val username = findViewById<EditText>(R.id.etUsername).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()

            if(validateInput(username, password)) {
                loginUser(username, password)
            }
        }
    }

    // Validates Inputs
    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            showErrorToast("Please enter a valid email")
            return false
        }
        if (password.isEmpty()) {
            showErrorToast("Password is required")
            return false
        }
        return true
    }

    // Login to application
    private fun loginUser(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val user = db.userDao().getUserByEmailAndPassword(email, password)

            runOnUiThread {
                if (user != null) {
                    showSuccessToast("Login successful!")
                    val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    showErrorToast("Invalid email or password")
                }
            }
        }
    }

    // Raise alert
    private fun showErrorToast(message: String) {
        val layoutInflater = layoutInflater
        val customView = layoutInflater.inflate(R.layout.custom_toast_error, null)

        // Set the message text
        val messageTextView = customView.findViewById<TextView>(R.id.toast_message)
        messageTextView.text = message

        // Create and show the Toast
        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = customView
        toast.show()
    }

    private fun showSuccessToast(message: String) {
        val layoutInflater = layoutInflater
        val customView = layoutInflater.inflate(R.layout.custom_toast_success, null)

        // Set the message text
        val messageTextView = customView.findViewById<TextView>(R.id.toast_message)
        messageTextView.text = message

        // Create and show the Toast
        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = customView
        toast.show()
    }}