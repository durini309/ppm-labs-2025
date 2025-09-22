package com.gt.uvg.rickandmorty.presentation.locationFeature.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gt.uvg.rickandmorty.data.LocationDb
import com.gt.uvg.rickandmorty.presentation.locationFeature.LocationRoutes
import com.gt.uvg.rickandmorty.presentation.model.Location
import com.gt.uvg.rickandmorty.ui.theme.RickAndMortyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationListScreen(
    locations: List<Location>,
    onLocationClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            TopAppBar(
                title = {
                    Text("Locations")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                )
            )
        }
    ) {

        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(locations) { item ->
                LocationItem(
                    location = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onLocationClick(item.id) }
                )
            }
        }
    }
}

@Composable
private fun LocationItem(
    location: Location,
    modifier: Modifier = Modifier
) {
    val imageBackgroundColors = listOf(
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.inverseSurface
    )
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            Text(text = location.name)
            Text(
                text = location.type,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

fun NavGraphBuilder.locationsListRoute(
    onLocationClick: (Int) -> Unit,
) {
    composable<LocationRoutes.LocationList> {
        val locationDb = LocationDb()
        val locations = locationDb.getAllLocations()
        LocationListScreen(
            locations = locations,
            onLocationClick = onLocationClick,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLocationListScreen() {
    RickAndMortyTheme {
        Surface {
            val locationDb = LocationDb()
            LocationListScreen(
                locations = locationDb.getAllLocations(),
                onLocationClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}