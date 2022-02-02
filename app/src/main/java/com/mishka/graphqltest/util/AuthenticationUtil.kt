package com.mishka.graphqltest.util

object AuthenticationUtil {

    val users = mutableListOf("antonio.lopez@wizeline.com", "peter@mail.com")

    /**
     * userName no puede estar vacío
     * password no puede estar vacía
     * password debe tener al menos una mayúscula
     * password longitud mayor de 6 caracteres
     */
    fun validateLoginInput(
        userName: String,
        password: String
    ): Boolean {
        return userName.isNotEmpty() &&
                password.isNotEmpty() &&
                password.length > 6 &&
                password.contains("[A-Z]+".toRegex())
    }

}