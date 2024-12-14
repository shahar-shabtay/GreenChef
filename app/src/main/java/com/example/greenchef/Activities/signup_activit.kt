package com.example.greenchef

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.os.Message
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.greenchef.Database.AppDatabase
import com.example.greenchef.Models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)

        // Navigation
        val btnTabSignIn = findViewById<Button>(R.id.btnTabSignIn)
        val btnWelcome = findViewById<ImageView>(R.id.btnBackArrow)

        btnTabSignIn.setOnClickListener {
            val loginIntent = Intent(this, SignInActivity::class.java)
            startActivity(loginIntent)

            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        btnWelcome.setOnClickListener {
            val welcomeIntent = Intent(this, WelcomeActivity::class.java)
            startActivity(welcomeIntent)

            // Apply custom animation for activity transition
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        // SignUp
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (validateInputs(username, email, password)) {
                saveUserToDatabase(username, email, password)
            }
        }
    }

    // Validate function
    private fun validateInputs(username: String, email: String, password: String):Boolean {
        if(username.isEmpty()) {
            showErrorToast("Username is required.")
            return false
        }

        if(username.length < 3) {
            showErrorToast("Username must to be at least 3 characters.")
            return false
        }

        if(email.isEmpty()) {
            showErrorToast("Email is required.")
            return false
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showErrorToast("Enter a valid email address.")
            return false
        }

        if(password.isEmpty()) {
            showErrorToast("Password is required.")
            return false
        }

        if(password.length < 6){
            showErrorToast("Password must to be at least 6 characters")
            return false
        }

        if(!password.any { it.isDigit() }) {
            showErrorToast("Password must contain at least one number.")
            return false
        }

        return true
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
    }
    // Save to Dastabase
    private fun saveUserToDatabase(username: String, email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val userExists = db.userDao().isUserExists(email) > 0

            runOnUiThread {
                if (userExists) {
                    showErrorToast("Email already registered")
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        db.userDao().insertUser(
                            User(
                                username = username,
                                email = email,
                                password = password
                            )
                        )
                        runOnUiThread {
                            showSuccessToast("User registered successfully")
                            finish()
                        }
                    }
                }
            }
        }
    }
}