package com.example.foodorderingapp

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
//import com.example.foodorderingapp.databinding.ActivityLoginBinding
import com.example.foodorderingapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        try{
        //Initialize firebase Auth
        auth = FirebaseAuth.getInstance()
//
        binding.alreadyHaveButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        val ed = findViewById<EditText>(R.id.name)
        ed.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && ed.text.toString().isNotEmpty()) {
                Toast.makeText(applicationContext, ed.text.toString(), Toast.LENGTH_SHORT).show()
            }
        }


        binding.signUpButton.setOnClickListener {
            //get text
            val name = binding.name.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            //check if any field is blank
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill the Details", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        try{
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Registration Failed: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        } catch (e: Exception) {
                            // Handle exception
                            Toast.makeText(this, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        } catch (e: Exception) {
            // Handle exception
            Toast.makeText(this, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
        } finally {
            val ed = findViewById<EditText>(R.id.name)
            ed.setOnFocusChangeListener(null)
        }
    }
}

