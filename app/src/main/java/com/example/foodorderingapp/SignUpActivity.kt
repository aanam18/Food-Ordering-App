package com.example.foodorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodorderingapp.databinding.ActivityLoginBinding
import com.example.foodorderingapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //Initialize firebase Auth
        auth = FirebaseAuth.getInstance()
//        l
//            binding.alreadyHaveButton.setOnClickListener {
//                startActivity(Intent(this, LoginActivity::class.java))
//                finish()
//        }

            binding.signUpButton.setOnClickListener{
                //get text
                val name = binding.name.text.toString()
                val email = binding.email.text.toString()
                val password = binding.password.text.toString()

                //check if any field is blank
                if (name.isEmpty() ||email.isEmpty()||password.isEmpty()){
                    Toast.makeText(this, "Please fill the Details", Toast.LENGTH_SHORT).show()
                }else{
                    auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(this){ task ->
                        if (task.isSuccessful){
                            Toast.makeText(this,"Registration Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                            else{
                            Toast.makeText(this,"Registration Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                        }
                }
            }
        }
    }