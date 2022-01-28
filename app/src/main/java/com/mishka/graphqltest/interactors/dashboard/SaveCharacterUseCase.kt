package com.mishka.graphqltest.interactors.dashboard

import com.mishka.graphqltest.data.repository.CharacterRepository
import com.mishka.graphqltest.domain.model.CharacterModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaveCharacterUseCase @Inject constructor(private val characterDetailRepository: CharacterRepository) {

    fun invoke(characterModel: CharacterModel) {
        CoroutineScope(Dispatchers.IO).launch {
            characterDetailRepository.saveCharacterSingle(characterModel)
        }
    }


}
