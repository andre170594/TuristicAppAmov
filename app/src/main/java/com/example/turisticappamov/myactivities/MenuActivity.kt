package com.example.turisticappamov.myactivities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.lifecycleScope
import com.example.turisticappamov.mymodels.FeedItem
import com.example.turisticappamov.mymodels.User
import com.example.turisticappamov.navigation.AppNavigation
import com.example.turisticappamov.ui.theme.TuristicAppAmovTheme
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class MenuActivity : ComponentActivity() {

    private lateinit var activeUser: User
    private lateinit var liveFeed: ArrayList<FeedItem>
    private lateinit var listNumQuestionExam: MutableList<Int>
    private lateinit var listNumQuestionExamRef: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var feedsRef: DatabaseReference
    private var optNightMode = mutableStateOf(false)
    private var optNotificationsOn = mutableStateOf(true)
    private var optShowFeed = mutableStateOf(true)
    private lateinit var listaSettingsOpts: ArrayList<MutableState<Boolean>>
    private lateinit var listAvailableExams: MutableList<String>


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

            // obtain NumQuestionPerExam
            listNumQuestionExam = ArrayList()
            try {
                database = FirebaseDatabase.getInstance()
                listNumQuestionExamRef = database.getReference("examNumQ")
                val dataSnapshot = listNumQuestionExamRef.get().await()
                listNumQuestionExam = dataSnapshot.children.map {
                    it.getValue(Int::class.java) ?: 0
                }.toMutableList()
            } catch (e: Exception) {
                println("Failed to retrieve question numbers: ${e.message}")
            }

            // Obtain available exam names
            try {
                val examNamesSnapshot = listNumQuestionExamRef.get().await()
                listAvailableExams = examNamesSnapshot.children.mapNotNull {
                    it.key // Get the key (exam name)
                }.toMutableList()

            } catch (e: Exception) {
                println("Failed to retrieve exam names: ${e.message}")
            }


            // track options in settings screen
            listaSettingsOpts = ArrayList()
            optNightMode.value = activeUser.goDark ?: false
            optShowFeed.value = activeUser.goFeed ?: false
            listaSettingsOpts.add(optNightMode)
            listaSettingsOpts.add(optNotificationsOn)
            listaSettingsOpts.add(optShowFeed)

            setContent {
                TuristicAppAmovTheme {
                    // update dark mode na BD
                    if(listaSettingsOpts[0].value){
                        activeUser.goDark = true
                        val usersRef = Firebase.database.reference.child("users")
                        val userRef = usersRef.child(activeUser.userID.toString())
                        userRef.child("goDark").setValue(activeUser.goDark)

                    }else{
                        activeUser.goDark = false
                        val usersRef = Firebase.database.reference.child("users")
                        val userRef = usersRef.child(activeUser.userID.toString())
                        userRef.child("goDark").setValue(activeUser.goDark)
                    }
                    // update Feed na BD
                    if(listaSettingsOpts[2].value){
                        activeUser.goFeed = true
                        val usersRef = Firebase.database.reference.child("users")
                        val userRef = usersRef.child(activeUser.userID.toString())
                        userRef.child("goFeed").setValue(activeUser.goFeed)

                    }else{
                        activeUser.goFeed = false
                        val usersRef = Firebase.database.reference.child("users")
                        val userRef = usersRef.child(activeUser.userID.toString())
                        userRef.child("goFeed").setValue(activeUser.goFeed)
                    }
                    AppNavigation(activeUser, liveFeed, intent, content,listaSettingsOpts,this@MenuActivity,
                        listNumQuestionExam as ArrayList<Int>,listAvailableExams as ArrayList<String>
                    )
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
