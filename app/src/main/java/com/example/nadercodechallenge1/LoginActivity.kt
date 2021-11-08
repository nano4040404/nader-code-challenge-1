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
import com.google.android.material.textfield.TextInputEditText
import es.dmoral.toasty.Toasty
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private val loginBtn by lazy { findViewById<Button>(R.id.loginBtn) }
    private val et_password by lazy { findViewById<TextInputEditText>(R.id.et_password)}
    private val et_email by lazy { findViewById<TextInputEditText>(R.id.et_email)}
    private val btn_signup by lazy { findViewById<Button>(R.id.btn_signup)}

    lateinit var shared : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        shared = getSharedPreferences("logindata" , Context.MODE_PRIVATE)
        val account = shared.getString("email",null)
        if (account != null){
            startActivity(Intent(this, WelcomeActivity::class.java))
            this.finish()
        }
        btn_signup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        loginBtn.setOnClickListener {

            val sharedemail : String? = shared.getString("email",null)
            val sharedpass : String? = shared.getString("password",null)
            if(sharedemail.equals(et_email.text.toString().trim()) &&
                sharedpass.equals(et_password.text.toString().trim())){
                startActivity(Intent(this, WelcomeActivity::class.java))
                this.finish()
                Toasty.success(this, "Success! Welcome ${shared.getString("firstname","")}", Toast.LENGTH_SHORT, true).show()
            }else{
                Toasty.error(this, "Error! User does not exist", Toast.LENGTH_SHORT, true).show()
            }


        }
    }


}