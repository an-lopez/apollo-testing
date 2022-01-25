package com.mishka.graphqltest.domain.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mishka.graphqltest.interactors.dashboard.CharacterViewState
import com.mishka.graphqltest.interactors.dashboard.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharactersDashboardViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase):
    ViewModel() {

    private val _viewState = MutableLiveData<CharacterViewState>()

    val viewState: LiveData<CharacterViewState> = _viewState

    init {
        getCharacters()
    }

    private fun getCharacters() {
        Timber.tag(CharactersDashboardViewModel::class.simpleName.toString()).d("Se invoca getCharactersUseCase")
        getCharactersUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .subscribe {
                _viewState.postValue(it)
            }
    }

}