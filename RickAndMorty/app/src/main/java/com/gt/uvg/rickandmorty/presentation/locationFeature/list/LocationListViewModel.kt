package com.gt.uvg.rickandmorty.presentation.locationFeature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gt.uvg.rickandmorty.data.LocationDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationListViewModel: ViewModel() {
    private val _state = MutableStateFlow(LocationListScreenState())
    val state = _state.asStateFlow()
    private val locationDb = LocationDb()

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            _state.update { LocationListScreenState() }
            delay(4000L)
            val numRandom = (0..10).random()
            if (numRandom % 2 == 0) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        data = locationDb.getAllLocations()
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