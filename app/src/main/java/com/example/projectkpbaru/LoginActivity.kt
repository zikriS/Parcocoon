package com.example.projectkpbaru

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectkpbaru.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()

        if (firebaseAuth.currentUser != null) {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        //gotoregister text view
        binding.gotoRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        //gotoforgotpassword
        binding.tvForgotPass.setOnClickListener {
            val intent = Intent(this, ForgotPassActivity::class.java)
            startActivity(intent)
        }
        //button untuk login
        binding.loginBtn.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val pass = binding.inputPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {

                        Firebase.database.getReference("user")
                            .child(Firebase.auth.currentUser!!.uid).get()
                            .addOnSuccessListener { snapshot ->
                                if (snapshot.exists()) {
                                    val sharedPref =
                                        getSharedPreferences("userProfile", Context.MODE_PRIVATE)
                                    with(sharedPref.edit()) {
                                        putString(
                                            "username",
                                            snapshot.child("username").value as String
                                        )
                                        apply()
                                    }
                                }
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                Toast.makeText(this, "Selamat Datang", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(
                            this,
                            "Email atau Password yang anda masukan SALAH",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }


            } else {
                Toast.makeText(this, "Area tidak boleh kosong !!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}