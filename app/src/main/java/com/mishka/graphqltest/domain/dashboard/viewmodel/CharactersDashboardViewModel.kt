package com.mishka.graphqltest.domain.dashboard.viewmodel

import androidx.lifecycle.*
import com.mishka.graphqltest.interactors.dashboard.CharacterViewState
import com.mishka.graphqltest.interactors.dashboard.GetCharactersUseCase
import com.mishka.graphqltest.util.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharactersDashboardViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val state: SavedStateHandle
) :
    ViewModel() {

    private val _viewState = MutableLiveData<CharacterViewState>()

    val viewState: LiveData<CharacterViewState> = _viewState

    private var getNotesJob: Job? = null

    fun getCharacters(order: Order) {
        Timber.tag(CharactersDashboardViewModel::class.simpleName.toString())
            .d("Se invoca getCharactersUseCase")
        getNotesJob?.cancel()
        getNotesJob = getCharactersUseCase.invoke(order).onEach {
            _viewState.postValue(it)
        }.launchIn(viewModelScope)
    }

}