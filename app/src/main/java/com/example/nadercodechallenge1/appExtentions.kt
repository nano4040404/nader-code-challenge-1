package com.example.nadercodechallenge1

import com.google.android.material.textfield.TextInputEditText


fun TextInputEditText.getTextTrimed() : String{
    return this.text.toString().trim()
}
