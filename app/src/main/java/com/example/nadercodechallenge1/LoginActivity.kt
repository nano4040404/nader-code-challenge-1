package com.example.nadercodechallenge1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.nadercodechallenge1.databinding.ActivityLoginBinding
import com.example.nadercodechallenge1.databinding.ActivitySignUpBinding
import com.example.nadercodechallenge1.databinding.ActivityWelcomeBinding
import com.google.android.material.textfield.TextInputEditText
import es.dmoral.toasty.Toasty
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (checkAccount()){
            startActivity(Intent(this, WelcomeActivity::class.java))
            this.finish()
        }
        binding.signupBtn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.loginBtn.setOnClickListener {

            if(verifyAccount(binding.emailField.getTextTrimed(),binding.passwordField.getTextTrimed())){
                startActivity(Intent(this, WelcomeActivity::class.java))
                this.finish()
                Toasty.success(this, "Success! Welcome ${getCurrentAccount().firstName}", Toast.LENGTH_SHORT, true).show()
            }else{
                Toasty.error(this, "Error! User does not exist", Toast.LENGTH_SHORT, true).show()
            }


        }
    }


}