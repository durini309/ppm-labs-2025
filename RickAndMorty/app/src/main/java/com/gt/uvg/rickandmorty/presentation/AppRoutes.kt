package com.gt.uvg.rickandmorty.presentation

import kotlinx.serialization.Serializable

sealed interface AppRoutes {
    @Serializable
    data object Login : AppRoutes

    @Serializable
    data object LoggedFlow: AppRoutes
}