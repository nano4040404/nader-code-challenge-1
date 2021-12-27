package com.example.nadercodechallenge1.presentation.ui

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.textfield.TextInputEditText


fun TextInputEditText.getTextTrimed() : String{
    return this.text.toString().trim()
}

fun View.setVisibility(state: Boolean){
     if (state){
        this.visibility = View.VISIBLE
    }else{
        this.visibility = View.INVISIBLE
    }
}

//fun Activity.setTheme(theme: Int){
//    when (theme) {
//        0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//
//        1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//
//        2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
//    }
//    saveTheme(theme)
//
//}
fun Activity.changeTheme(){
    when (getCurrentTheme()) {
        0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }


}

