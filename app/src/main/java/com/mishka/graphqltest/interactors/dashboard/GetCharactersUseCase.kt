package com.mishka.graphqltest.interactors.dashboard

import com.mishka.graphqltest.data.repository.CharacterRepository
import com.mishka.graphqltest.util.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharacterRepository) {

    fun invoke(order: Order = Order.AtoZ): Flow<CharacterViewState> {
        return repository.getCharactersRemote().map {
            CharacterViewState(characters = when (order) {
                is Order.ZtoA -> it.sortedByDescending { character -> character.name }
                is Order.AtoZ -> it.sortedBy { character -> character.name }
            })
        }
    }
}