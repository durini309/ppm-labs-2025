package com.gt.uvg.rickandmorty.presentation

import kotlinx.serialization.Serializable

sealed interface AppRoutes {
    @Serializable
    object Login : AppRoutes

    @Serializable
    object Characters : AppRoutes

    @Serializable
    data class CharacterProfile(
        val id: Int
    ) : AppRoutes
}