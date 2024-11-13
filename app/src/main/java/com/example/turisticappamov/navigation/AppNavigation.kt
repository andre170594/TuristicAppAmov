package com.example.turisticappamov.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.turisticappamov.myactivities.MenuActivity
import com.example.turisticappamov.mymodels.FeedItem
import com.example.turisticappamov.mymodels.User
import com.example.turisticappamov.screens.HomeScreen
import com.example.turisticappamov.screens.ProfileScreen
import com.example.turisticappamov.screens.SettingsScreen
import com.example.turisticappamov.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    user: User,
    listFeedItems: ArrayList<FeedItem>?,
    intent: Intent,
    content: Context,
    listaSettings: ArrayList<MutableState<Boolean>>,
    menuActivity: MenuActivity
){
    val navController:NavHostController = rememberNavController()

    val navBarTextColor = if (user.goDark == false) LightNavbarTextColor else DarkNavbarTextColor
    val navBarIconColor = if (user.goDark == false) LightNavbarIconColor else DarkNavbarIconColor
    val navBarColor = if (user.goDark == false) LightNavbarColor else DarkNavbarColor

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = navBarColor
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                listOfNavItems.forEach{ navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                        onClick = {
                                  navController.navigate(navItem.route){
                                      popUpTo(navController.graph.findStartDestination().id){
                                          saveState =true
                                      }
                                      launchSingleTop = true
                                      restoreState = true
                                  }
                        },
                        icon = {
                            Icon(
                                imageVector = navItem.icon ,
                                contentDescription = null,
                                tint = navBarIconColor
                            )
                        },
                        label = {
                            Text(text = navItem.label, color = navBarTextColor)
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screens.HomeScreen.name,
            modifier = Modifier.padding(it),
            enterTransition = {
                scaleIn(initialScale = 0.8f) + fadeIn()
            },
            exitTransition = {
                scaleOut(targetScale = 1.2f) + fadeOut()
            },
            popEnterTransition = {
                scaleIn(initialScale = 0.8f) + fadeIn()
            },
            popExitTransition = {
                scaleOut(targetScale = 1.2f) + fadeOut()
            }

        ){
            composable(route = Screens.HomeScreen.name){
                HomeScreen(user,intent,content)
            }
            composable(route = Screens.ProfileScreen.name){
                ProfileScreen(user,listFeedItems)
            }
            composable(route = Screens.SettingsScreen.name){
                SettingsScreen(user,listaSettings,menuActivity)
            }
        }
    }
}
