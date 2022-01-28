package com.mishka.graphqltest.interactors.dashboard

import com.google.common.truth.Truth.assertThat
import com.mishka.graphqltest.data.repository.FakeCharacterRepository
import com.mishka.graphqltest.util.Order
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class AlternativeGetCharactersUseCaseTest {

    private lateinit var useCase: GetCharactersUseCase

    @Before
    fun setUp() {
        useCase = GetCharactersUseCase(FakeCharacterRepository())
    }

    @Test
    fun `order of character by ascending, correct order`() = runBlocking {
        val characters = useCase.invoke().first()
        assertThat(characters.characters).isNotNull()
        for (i in 0..characters.characters!!.size - 2) {
            println("${characters.characters!![i].name} is less than ${characters.characters!![i + 1].name}")
            assertThat(characters.characters!![i].name).isLessThan(characters.characters!![i + 1].name)
        }
    }

    @Test
    fun `order of character by descending, correct order`(): Unit = runBlocking {
        val characters = useCase.invoke(Order.ZtoA).first()
        assertThat(characters.characters).isNotNull()
        for (i in 0..characters.characters!!.size - 2) {
            println("${characters.characters!![i].name} is greater than ${characters.characters!![i + 1].name}")
            assertThat(characters.characters!![i].name).isGreaterThan(characters.characters!![i + 1].name)
        }
    }

}