package com.example.foodorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodorderingapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        //check if user already logged iN
        val currentUser :FirebaseUser? =auth.currentUser
        if (currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Initialize
        auth = FirebaseAuth.getInstance()

        binding.loginbutton.setOnClickListener{
//        val intent = Intent(this,SignUpActivity::class.java)
//            startActivity(intent)
            val EmailLogin=binding.EmailLogin.text.toString()
            val LoginPass=binding.LoginPass.text.toString()
            if (EmailLogin.isEmpty()||LoginPass.isEmpty()){
                    Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(EmailLogin,LoginPass)
                    .addOnCompleteListener{task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Sign In successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this, "Sign In Failed ${task.exception?.message}" , Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }
        binding.dontHaveButton.setOnClickListener {
            val intent = Intent (this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}