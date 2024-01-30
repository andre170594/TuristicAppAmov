package com.example.turisticappamov.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val listOfNavItems: List<NavItem> = listOf(
    NavItem(
        label = "Test",
        icon = Icons.Default.Create,
        route = Screens.HomeScreen.name
    ),NavItem(
        label = "Feed",
        icon = Icons.Default.Person,
        route = Screens.ProfileScreen.name
    ),NavItem(
        label = "Settings",
        icon = Icons.Default.Build,
        route = Screens.SettingsScreen.name
    )
)