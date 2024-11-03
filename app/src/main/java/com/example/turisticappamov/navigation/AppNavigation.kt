package com.example.turisticappamov.navigation

import android.content.Context
import android.content.Intent

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
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
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.turisticappamov.mymodels.FeedItem
import com.example.turisticappamov.mymodels.User
import com.example.turisticappamov.screens.HomeScreen
import com.example.turisticappamov.screens.ProfileScreen
import com.example.turisticappamov.screens.SettingsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    user: User,
    listFeedItems: ArrayList<FeedItem>?,
    intent: Intent,
    content: Context,
    listaSettings: ArrayList<MutableState<Boolean>>,
    lazyListState: LazyListState
){
    val navController:NavHostController = rememberNavController()

    // TODO
    // rest of the color of navBar
    var navBarTextColor = Color(0xFFE2E7EB)
    var navBarIconColor = Color(0xFF52534D)
    var navBarColor = Color(0xFF4E6C50)
    if(user.goDark==true){
        navBarColor = Color(0xFF111644)
        navBarTextColor = Color(0xFFCAD7DF)
        navBarIconColor = Color(0xFFCE93D8)
    }

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
                                contentDescription = null, tint = navBarIconColor
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
            modifier = Modifier
                .padding(it)

        ){
            composable(route = Screens.HomeScreen.name){
                HomeScreen(user,intent,content)
            }
            composable(route = Screens.ProfileScreen.name){
                ProfileScreen(user,listFeedItems,lazyListState)
            }
            composable(route = Screens.SettingsScreen.name){
                SettingsScreen(user,listaSettings)
            }
        }
    }
}
