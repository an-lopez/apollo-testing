package com.mishka.graphqltest.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AuthenticationUtilTest{

    @Test
    fun `empty username returns false`(){
        val result = AuthenticationUtil.validateLoginInput("", "password")
        assertThat(result).isFalse()
    }

    @Test
    fun `password length less than 6 characters returns false`(){
        val result = AuthenticationUtil.validateLoginInput("antonio.lopez@wizeline.com", "pass")
        assertThat(result).isFalse()
    }

    @Test
    fun `empty username and two character password returns false`(){
        val result = AuthenticationUtil.validateLoginInput("", "pw")
        assertThat(result).isFalse()
    }

    @Test
    fun `non empty username and seven character password returns true`(){
        val result = AuthenticationUtil.validateLoginInput("antonio.lopez@wizeline.com", "password")
        assertThat(result).isTrue()
    }



}