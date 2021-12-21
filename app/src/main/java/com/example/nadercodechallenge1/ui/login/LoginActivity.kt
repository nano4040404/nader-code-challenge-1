package com.example.nadercodechallenge1.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nadercodechallenge1.databinding.ActivityLoginBinding
import com.example.nadercodechallenge1.ui.checkAccount
import com.example.nadercodechallenge1.ui.getCurrentAccount
import com.example.nadercodechallenge1.ui.getTextTrimed
import com.example.nadercodechallenge1.ui.signup.SignUpActivity
import com.example.nadercodechallenge1.ui.verifyAccount
import com.example.nadercodechallenge1.ui.welcome.WelcomeActivity
import es.dmoral.toasty.Toasty

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