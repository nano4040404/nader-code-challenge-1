package com.example.nadercodechallenge1.internal.Utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ValidationManagerTest{

    @Test
    fun whenEmailIsValid(){
        val email = "nano@gmail.com"
        val result = ValidationManager.isValidEmail(email)

        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenEmailIsInvalid(){
        val emails = arrayOf("nanogmail.com","nanogmail@.com","@anogmail.com")
        for (email in emails){
            val result = ValidationManager.isValidEmail(email)
            assertThat(result).isEqualTo(false)
        }
    }

    @Test
    fun whenPasswordIsValid(){
        val passwords = arrayOf("Test@1289","UnitW!783","kdjU89$$")
        for (password in passwords){
            val result = ValidationManager.isValidPassword(password)
            assertThat(result).isEqualTo(true)
        }
    }

    @Test
    fun whenPasswordIsNotValid(){
        val passwords = arrayOf("Test341289","UnitW783","kascsacascasc","123456789")
        for (password in passwords){
            val result = ValidationManager.isValidPassword(password)
            assertThat(result).isEqualTo(false)
        }
    }
    @Test
    fun whenPasswordIsMatch(){
        val pass1 = "password"
        val pass2 = "password"
        val result = ValidationManager.isPasswordMatch(pass1,pass2)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenPasswordIsNotMatch(){
        val pass1 = "password1"
        val pass2 = "password2"
        val result = ValidationManager.isPasswordMatch(pass1,pass2)
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenFieldIsValid(){

        val field = "pas"
        val result = ValidationManager.isFieldValid(field)
        assertThat(result).isEqualTo(true)
    }
    @Test
    fun whenFieldIsNotValid(){

        val field = "pa"
        val result = ValidationManager.isFieldValid(field)
        assertThat(result).isEqualTo(false)
    }

}