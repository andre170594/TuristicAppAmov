package com.example.turisticappamov.myactivities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.turisticappamov.mymodels.FeedItem
import com.example.turisticappamov.mymodels.User
import com.example.turisticappamov.navigation.AppNavigation
import com.example.turisticappamov.ui.theme.TuristicAppAmovTheme
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class MenuActivity : ComponentActivity() {

    private lateinit var activeUser: User
    private lateinit var liveFeed: ArrayList<FeedItem>
    private lateinit var database: FirebaseDatabase
    private lateinit var feedsRef: DatabaseReference

    private var optNightMode = mutableStateOf(false)
    private var optNotificationsOn = mutableStateOf(true)
    private var optShowFeed = mutableStateOf(true)
    private lateinit var listaSettingsOpts: ArrayList<MutableState<Boolean>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goFullScreen()

        activeUser = (intent.getSerializableExtra("USER") as? User)!!
        val intent = Intent(this@MenuActivity, TesteActivity::class.java)
        val content = this.baseContext

        // obtain feed
        liveFeed = ArrayList()
        lifecycleScope.launch {
            try {
                database = FirebaseDatabase.getInstance()
                feedsRef = database.getReference("feeds")
                val feedsList = withContext(Dispatchers.IO) {
                    val dataSnapshot = feedsRef.get().await()
                    val list = ArrayList<FeedItem>()
                    for (feedSnapshot in dataSnapshot.children) {
                        val contente =
                            feedSnapshot.child("content").getValue(String::class.java) ?: ""
                        val nameUser =
                            feedSnapshot.child("nameUser").getValue(String::class.java) ?: ""
                        val timestamp =
                            feedSnapshot.child("timestamp").getValue(Long::class.java) ?: 0L
                        val title = feedSnapshot.child("title").getValue(String::class.java) ?: ""
                        val feed = FeedItem(title, contente, timestamp, nameUser)
                        list.add(feed)
                    }
                    list
                }

                liveFeed = feedsList
            } catch (e: Exception) {
                println("Failed to retrieve feed: ${e.message}")
            }

            // track options in settings screen
            listaSettingsOpts = ArrayList()
            listaSettingsOpts.add(optNightMode)
            listaSettingsOpts.add(optNotificationsOn)
            listaSettingsOpts.add(optShowFeed)

            setContent {
                TuristicAppAmovTheme {
                    AppNavigation(activeUser, liveFeed, intent, content,listaSettingsOpts)
                }
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

@Preview
@Composable
fun Test(){
    val user = User("userID","Bulbassaur","pwd",89.5,null)
    var lista = ArrayList<MutableState<Boolean>>()
    AppNavigation(user,null, Intent(), LocalContext.current,lista)
}