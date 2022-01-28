package com.mishka.graphqltest.domain.dashboard.viewmodel

import androidx.lifecycle.*
import com.mishka.graphqltest.domain.dashboard.CharacterDetailViewState
import com.mishka.graphqltest.domain.model.CharacterModel
import com.mishka.graphqltest.interactors.dashboard.GetSingleCharacterUseCase
import com.mishka.graphqltest.interactors.dashboard.SaveCharacterUseCase
import com.mishka.graphqltest.interactors.dashboard.UpdateCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val saveCharacter: SaveCharacterUseCase,
    private val updateCharacterUseCase: UpdateCharacterUseCase,
    private val getSingleCharacterUseCase: GetSingleCharacterUseCase,
    state: SavedStateHandle
) : ViewModel() {

    private val _viewState = MutableLiveData<CharacterDetailViewState>()

    val viewState: LiveData<CharacterDetailViewState> = _viewState

    init {
        if (state.contains("id")) {
            viewModelScope.launch {
                getSingleCharacterUseCase.invoke(state.get("id")!!).also { viewState ->
                    _viewState.postValue(viewState)
                }

            }
        }
    }

    fun saveNewCharacter(characterModel: CharacterModel) {
        saveCharacter.invoke(characterModel)
    }

    fun updateCharacter(characterModel: CharacterModel) {
        viewModelScope.launch {
            updateCharacterUseCase.invoke(characterModel)
        }
    }

}