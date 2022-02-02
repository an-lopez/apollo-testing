package com.mishka.graphqltest.interactors.dashboard

import com.mishka.graphqltest.data.repository.CharacterRepository
import com.mishka.graphqltest.domain.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateCharacterUseCase @Inject constructor(private val characterDetailRepository: CharacterRepository) {

    suspend fun invoke(characterModel: CharacterModel) {
        withContext(Dispatchers.IO) {
            characterDetailRepository.updateCharacter(characterModel)
        }
    }

}
