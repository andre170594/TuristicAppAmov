package com.example.turisticappamov.myactivities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.turisticappamov.mymodels.User
import com.example.turisticappamov.navigation.AppNavigation
import com.example.turisticappamov.ui.theme.TuristicAppAmovTheme


class MenuActivity : ComponentActivity() {

    private lateinit var activeUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goFullScreen()

        activeUser= (intent.getSerializableExtra("USER") as? User)!!
        val intent = Intent(this@MenuActivity, TesteActivity::class.java)
        val content = this.baseContext

        setContent {
            TuristicAppAmovTheme {
                AppNavigation(activeUser,intent,content)
            }
        }
    }


    private fun goFullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}

