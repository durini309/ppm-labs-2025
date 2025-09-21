package com.gt.uvg.rickandmorty.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.gt.uvg.rickandmorty.presentation.characterFeature.list.characterListRoute
import com.gt.uvg.rickandmorty.presentation.characterFeature.profile.characterProfileRoute
import com.gt.uvg.rickandmorty.presentation.loginFeature.loginRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppRoutes.Login,
        modifier = modifier
    ) {
        loginRoute(
            onLoginClick = {
                navController.navigate(AppRoutes.Characters)
            }
        )

        characterListRoute(
            onCharacterClick = { characterId ->
                navController.navigate(
                    AppRoutes.CharacterProfile(id = characterId)
                )
            }
        )

        characterProfileRoute(
            onNavigateBack = {
                navController.popBackStack()
            }
        )
    }
}