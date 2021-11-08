package com.example.nadercodechallenge1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import es.dmoral.toasty.Toasty

class WelcomeActivity : AppCompatActivity() {
    lateinit var shared : SharedPreferences
    private val btn_logout by lazy { findViewById<Button>(R.id.btn_logout)}
    private val et_namelast by lazy { findViewById<TextView>(R.id.et_namelast) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        shared = getSharedPreferences("logindata" , Context.MODE_PRIVATE)

        et_namelast.text = "Welcome ${shared.getString("firstname","")} ${shared.getString("lastname","")}"
        btn_logout.setOnClickListener{
            val edit = shared.edit()
            edit.remove("firstname")
            edit.remove("lastname")
            edit.remove("email")
            edit.remove("password")
            edit.apply()
            Toasty.warning(this, "Account removed", Toast.LENGTH_SHORT, true).show()
            startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }

    }
}