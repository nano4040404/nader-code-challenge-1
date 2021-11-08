package com.example.nadercodechallenge1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import es.dmoral.toasty.Toasty
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    private val et_firstname by lazy { findViewById<TextInputEditText>(R.id.et_firstname)}
    private val et_lastname by lazy { findViewById<TextInputEditText>(R.id.et_lastname)}
    private val et_email by lazy { findViewById<TextInputEditText>(R.id.et_email)}
    private val et_password by lazy { findViewById<TextInputEditText>(R.id.et_password)}
    private val et_confirmpassword by lazy { findViewById<TextInputEditText>(R.id.et_confirmpassword)}
    private val btn_signup by lazy { findViewById<Button>(R.id.btn_signup)}

    lateinit var shared : SharedPreferences


    //et_firstname
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        shared = getSharedPreferences("logindata" , Context.MODE_PRIVATE)

        btn_signup.setOnClickListener{
            if(checkAllFields()){
                val edit = shared.edit()
                edit.putString("firstname" , et_firstname.text.toString().trim())
                edit.putString("lastname" , et_lastname.text.toString().trim())
                edit.putString("email" , et_email.text.toString().trim())
                edit.putString("password" , et_password.text.toString().trim())
                edit.apply()
                Toasty.success(this, "Success! ${shared.getString("email","")}", Toast.LENGTH_SHORT, true).show()
                this.finish()
            }else{
                Toasty.error(this, "Error! one of the field does not match input requirement", Toast.LENGTH_SHORT, true).show()
            }

        }
    }

    //check all fields
    fun checkAllFields() : Boolean{
        val firstName = et_firstname.text.toString().trim()
        val lastName = et_lastname.text.toString().trim()
        val email = et_email.text.toString().trim()
        val password = et_password.text.toString().trim()
        val passwordConf = et_confirmpassword.text.toString().trim()

        var emailfield = false
        var passwordfield = false
        var matchpasswordfield = false
        var fnamefield = false
        var lnamefield = false

        if(isValidEmail(email)){
            emailfield =true
        }else{
            et_email.error = "Please enter a valid email"
        }
        if(isValidPassword(password)){
            passwordfield =true
        }else{
            et_password.error = "Wrong format"
        }
        if(isPasswordMatch(password,passwordConf)){
            matchpasswordfield =true
        }else{
            et_confirmpassword.error = "Passwords does not match"
        }
        if(isFieldValid(firstName)){
            fnamefield =true
        }else{
            et_firstname.error = "Enter a valid name"
        }
        if(isFieldValid(lastName)){
            lnamefield =true
        }else{
            et_lastname.error = "Enter a valid name"
        }
        return emailfield && passwordfield && matchpasswordfield && fnamefield && lnamefield
    }

    //check if email is valid
    fun isValidEmail(email: String?) : Boolean{
        val pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
        val match = pattern.matcher(email)
        return match.matches()
    }

    //check if password is valid: contain at least 8 chars+at least 1 capital +speacial character
    fun isValidPassword(password: String?) : Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)
            return passwordMatcher.find(password) != null
        } ?: return false
    }

    fun isPasswordMatch(password1: String,password2: String) : Boolean{
        return password1 == password2
    }

    fun isFieldValid(field: String) : Boolean {
        if (field.length >=3){
            return true
        }
        return false
    }


}