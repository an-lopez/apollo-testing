package com.mishka.graphqltest.interactors.dashboard

import com.mishka.graphqltest.data.repository.CharacterRepository
import com.mishka.graphqltest.domain.dashboard.CharacterDetailViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSingleCharacterUseCase @Inject constructor(
    private val characterDetailRepository: CharacterRepository
) {

    suspend fun invoke(characterId: Int): CharacterDetailViewState =
        withContext(Dispatchers.IO){
            CharacterDetailViewState(
                characters = characterDetailRepository.getCharacterSingle(
                    characterId
                )
            )
        }




}