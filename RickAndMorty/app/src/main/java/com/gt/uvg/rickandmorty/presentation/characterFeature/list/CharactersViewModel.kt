package com.gt.uvg.rickandmorty.presentation.characterFeature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gt.uvg.rickandmorty.data.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersViewModel: ViewModel() {
    private val _state = MutableStateFlow(CharactersScreenState())
    val state = _state.asStateFlow()
    private val charactersDb = CharacterDb()

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            // Reiniciamos porque ya sabemos que vamos a mostrar el loading, poner el error false y quitar data
            _state.update { CharactersScreenState() }
            delay(4000L)
            val numRandom = (0..10).random()
            if (numRandom % 2 == 0) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        data = charactersDb.getAllCharacters()
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }
}