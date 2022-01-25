package com.mishka.graphqltest.domain.dashboard

import com.google.common.truth.Truth.assertThat
import com.mishka.graphqltest.data.repository.FakeCharacterDetailRepository
import com.mishka.graphqltest.interactors.dashboard.GetSingleCharacterUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class CharacterDetailViewModelTest {

    private lateinit var useCase: GetSingleCharacterUseCase

    private lateinit var fakeRepository: FakeCharacterDetailRepository

    @Before
    fun setUp() {
        fakeRepository = FakeCharacterDetailRepository()
        useCase = GetSingleCharacterUseCase(fakeRepository)
    }

    @Test
    fun `check repository returns character`(){
        runBlocking {
            val viewState = useCase.invoke(1).last()
            assertThat(viewState.characters).isNotNull()
            assertThat(viewState.characters?.name).isEqualTo("Rick Martinez")
        }
    }

    @Test
    fun `check repository returns id 1`(){
        runBlocking {
            val viewState = useCase.invoke(1).last()
            assertThat(viewState.characters).isNotNull()
            assertThat(viewState.characters?.id).isEqualTo(1)
        }
    }
    @Test
    fun `check repository doesn't return 1`(){
        runBlocking {
            val viewState = useCase.invoke(1).last()
            assertThat(viewState.characters).isNotNull()
            assertThat(viewState.characters?.id).isNotEqualTo(-1)
        }
    }

}