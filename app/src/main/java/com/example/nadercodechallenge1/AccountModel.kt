package com.example.nadercodechallenge1

class AccountModel(val firstName: String?,val LastName: String?,val email: String?,val password: String?) {

    override fun toString(): String {
        return "AccountModel(firstName=$firstName, LastName=$LastName, email=$email, password=$password)"
    }
}