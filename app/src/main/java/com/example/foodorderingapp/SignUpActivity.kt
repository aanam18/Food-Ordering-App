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
import com.example.foodorderingapp.model.UserModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.signin.internal.SignInClientImpl
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class SignUpActivity : AppCompatActivity() {
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var username: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var googleSignInClient: GoogleSignInClient

    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // Initialize firebase Auth
        auth = FirebaseAuth.getInstance()

        //Initialize Firebase database
        database = Firebase.database.reference

        binding.createAccountButton.setOnClickListener {
            username=binding.userName.text.toString()
            email=binding.emailAddress.text.toString().trim()
            password=binding.password.text.toString().trim()

            if (email.isBlank() || password.isBlank() || username.isBlank()){
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
            }else{
                createAccount(email,password)
            }
        }

        binding.alreadyHaveButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                // Account creation successful
                Toast.makeText(
                    this@SignUpActivity,
                    "Account created successfully!",
                    Toast.LENGTH_SHORT
                ).show()

                saveUserData()
                startActivity(Intent(this,LoginActivity::class.java))
                finish()

                // You can perform further actions after successful account creation here
            }
            .addOnFailureListener { exception ->
                // Account creation failed
                Toast.makeText(
                    this@SignUpActivity,
                    "Account creation failed: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun saveUserData() {

        //retrieve data from input field
        username = binding.userName.text.toString()
        password = binding.password.text.toString().trim()
        email = binding.emailAddress.text.toString().trim()

val user = UserModel(username, email, password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        //Save data to Firebase
        database.child("user").child(userId).setValue(user)


    }

}


//    private val binding: ActivitySignUpBinding by lazy {
//        ActivitySignUpBinding.inflate(layoutInflater)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//        try{
//        //Initialize firebase Auth
//        auth = FirebaseAuth.getInstance()
////
//        binding.alreadyHaveButton.setOnClickListener {
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//        }
//        val ed = findViewById<EditText>(R.id.name)
//        ed.setOnFocusChangeListener { v, hasFocus ->
//            if (!hasFocus && ed.text.toString().isNotEmpty()) {
//                Toast.makeText(applicationContext, ed.text.toString(), Toast.LENGTH_SHORT).show()
//            }
//        }
//
//
//        binding.signUpButton.setOnClickListener {
//            //get text
//            val name = binding.name.text.toString()
//            val email = binding.email.text.toString()
//            val password = binding.password.text.toString()
//
//            //check if any field is blank
//            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
//                Toast.makeText(this, "Please fill the Details", Toast.LENGTH_SHORT).show()
//            } else {
//                auth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this) { task ->
//                        try{
//                        if (task.isSuccessful) {
//                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT)
//                                .show()
//                            startActivity(Intent(this, LoginActivity::class.java))
//                            finish()
//                        } else {
//                            Toast.makeText(
//                                this,
//                                "Registration Failed: ${task.exception?.message}",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                        } catch (e: Exception) {
//                            // Handle exception
//                            Toast.makeText(this, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//            }
//        }
//        } catch (e: Exception) {
//            // Handle exception
//            Toast.makeText(this, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
//        } finally {
//            val ed = findViewById<EditText>(R.id.name)
//            ed.setOnFocusChangeListener(null)
//        }
//    }
//}
//
