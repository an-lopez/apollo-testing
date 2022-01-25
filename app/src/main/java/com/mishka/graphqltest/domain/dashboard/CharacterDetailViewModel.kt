package com.mishka.graphqltest.domain.dashboard

import androidx.lifecycle.*
import com.mishka.graphqltest.interactors.dashboard.CharacterDetailViewState
import com.mishka.graphqltest.interactors.dashboard.CharacterViewState
import com.mishka.graphqltest.interactors.dashboard.GetSingleCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getSingleCharacterUseCase: GetSingleCharacterUseCase,
    private val state: SavedStateHandle
) : ViewModel() {

    private val _viewState = MutableLiveData<CharacterDetailViewState>()

    val viewState: LiveData<CharacterDetailViewState> = _viewState

    init {
        getCharacterInfo()
    }

    private fun getCharacterInfo() {
        viewModelScope.launch {
            getSingleCharacterUseCase.invoke(state.get<Int>("id") ?: -1).collect {
                _viewState.postValue(it)
            }
        }
    }

}