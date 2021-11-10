package com.example.nadercodechallenge1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.nadercodechallenge1.databinding.ActivitySignUpBinding
import com.example.nadercodechallenge1.databinding.ActivityWelcomeBinding
import es.dmoral.toasty.Toasty

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val account = getCurrentAccount()

        binding.fullNameField.text = "Welcome ${account.firstName} ${account.LastName}"
        binding.logoutBtn.setOnClickListener{
            deleteAccount()
            Toasty.warning(this, "Account removed", Toast.LENGTH_SHORT, true).show()
            startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }

    }
}