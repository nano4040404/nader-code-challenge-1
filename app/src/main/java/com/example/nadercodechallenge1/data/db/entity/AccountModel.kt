package com.example.nadercodechallenge1.data.db.entity

class AccountModel(val firstName: String?,val lastName: String?,val email: String?,val password: String?) {

    override fun toString(): String {
        return "AccountModel(firstName=$firstName, LastName=$lastName, email=$email, password=$password)"
    }
}