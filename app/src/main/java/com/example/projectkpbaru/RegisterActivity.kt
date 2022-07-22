package com.example.projectkpbaru

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectkpbaru.databinding.ActivityRegisterBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SignUpbtn.setOnClickListener {
            if (binding.editTextInputPass.text.toString() != binding.editTextInputRetypePass.text.toString()) {
                return@setOnClickListener
            }
            val userName = binding.editTextTextPersonName.text.toString()
            val userEmail = binding.editTextInputEmail.text.toString()
            val userPassword = binding.editTextInputPass.text.toString()
            register(userEmail, userPassword, userName)
        }
    }

    private fun register(userEmail : String, userPassword : String, userName : String) {
        Firebase.auth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val data = User(userName, userEmail)
                    Firebase.database.getReference("user").child(Firebase.auth.currentUser!!.uid)
                        .setValue(data).addOnSuccessListener {
                        Intent(this, MainActivity::class.java).also {
                            startActivity(it)
                            finish()
                        }
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Registrasi gagal. Error : ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}