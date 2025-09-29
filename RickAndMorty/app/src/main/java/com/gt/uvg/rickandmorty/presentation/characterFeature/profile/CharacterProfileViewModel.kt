package com.gt.uvg.rickandmorty.presentation.characterFeature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gt.uvg.rickandmorty.data.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterProfileViewModel(private val characterId: Int): ViewModel() {
    private val _state = MutableStateFlow(CharacterProfileScreenState())
    val state = _state.asStateFlow()
    private val charactersDb = CharacterDb()

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            _state.update { CharacterProfileScreenState() }
            delay(2000L)
            val numRandom = (0..10).random()
            if (numRandom % 2 == 0) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        data = charactersDb.getCharacterById(characterId)
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