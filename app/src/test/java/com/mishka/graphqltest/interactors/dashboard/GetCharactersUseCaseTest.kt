package com.mishka.graphqltest.interactors.dashboard

import com.google.common.truth.Truth.assertThat
import com.mishka.graphqltest.data.repository.CharacterRepository
import com.mishka.graphqltest.domain.model.CharacterModel
import com.mishka.graphqltest.domain.model.OriginModel
import com.mishka.graphqltest.util.Order
import io.mockk.every
import io.mockk.mockkClass
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class GetCharactersUseCaseTest {

    private lateinit var useCase: GetCharactersUseCase

    @Before
    fun setUp() {
        val repository = mockkClass(CharacterRepository::class)
        every { repository.getCharactersRemote() } returns flow {
            emit(
                listOf(
                    CharacterModel(
                        0,
                        "Pedro",
                        OriginModel(0, "", "", ""),
                        "0",
                        "0",
                        emptyList()
                    ),
                    CharacterModel(
                        2,
                        "Juan",
                        OriginModel(0, "", "", ""),
                        "0",
                        "0",
                        emptyList()
                    )
                )
            )
        }

        useCase = GetCharactersUseCase(repository)
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
    fun `order of character by descending, correct order`() = runBlocking {
        val characters = useCase.invoke(Order.ZtoA).first()
        assertThat(characters.characters).isNotNull()
        for (i in 0..characters.characters!!.size - 2) {
            println("${characters.characters!![i].name} is greater than ${characters.characters!![i + 1].name}")
            assertThat(characters.characters!![i].name).isGreaterThan(characters.characters!![i + 1].name)
        }
    }

}