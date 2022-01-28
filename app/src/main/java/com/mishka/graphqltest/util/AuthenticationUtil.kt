package com.mishka.graphqltest.util

object AuthenticationUtil{

    val users = mutableListOf("antonio.lopez@wizeline.com", "peter@mail.com")

    /**
     * the input is invalid if
     * ... userName is empty
     * ... password length is less than 6 characters
     */
    fun validateLoginInput(
        userName: String,
        password: String
    ): Boolean{
        return userName.isNotEmpty() && password.length > 6
    }

}