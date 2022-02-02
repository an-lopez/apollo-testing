package com.mishka.graphqltest.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AuthenticationUtilPresentationTest{

    @Test
    fun `usuario incorrecto y contrasena incorrecta retorna falso`(){
        val result = AuthenticationUtil.validateLoginInput("", "")
        assertThat(result).isFalse()
    }

    @Test
    fun `usuario correcto y contrasena incorrecta retorna falso`(){
        val result = AuthenticationUtil.validateLoginInput("antonio.lopez@wizeline.com", "")
        assertThat(result).isFalse()
    }

    @Test
    fun `usuario correcto y contrasena correcta retorna verdadero`(){
        val result = AuthenticationUtil.validateLoginInput("antonio.lopez@wizeline.com", "Contrasena1")
        assertThat(result).isTrue()
    }

    @Test
    fun `string equals expected string`(){
        val expected = "Juan come manzanas"
        assertThat(expected).isEqualTo("Juan Come Manzanas")
    }

    @Test
    fun `2 plus 2 equals 4`(){
        val expected = 4
        val operation = 2+2
        assertThat(operation).isEqualTo(expected)
    }

}