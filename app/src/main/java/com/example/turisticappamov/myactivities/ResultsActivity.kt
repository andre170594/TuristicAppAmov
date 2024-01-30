package com.example.turisticappamov.myactivities
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp

import com.example.turisticappamov.myactivities.ui.theme.TuristicAppAmovTheme

import com.example.turisticappamov.mylayouts.MyQuestion
import com.example.turisticappamov.mylayouts.MyResultsOption
import com.example.turisticappamov.mylayouts.RoundProgressBar
import com.example.turisticappamov.mymodels.ParOptionsAnswers
import com.example.turisticappamov.mymodels.Question
import com.example.turisticappamov.mymodels.User
import kotlin.math.roundToLong

class ResultsActivity : ComponentActivity() {

    private var numCertas:Int = 10
    private lateinit var listaErradas: ArrayList<Question>
    private lateinit var activeUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goFullScreen()

        numCertas = intent.getIntExtra("NUM_CERTAS",0)
        listaErradas = intent.getSerializableExtra("WRONG_QUESTIONS") as ArrayList<Question>
        activeUser= (intent.getSerializableExtra("USER") as? User)!!


        val startColor = Color(0xFF44617E)
        val endColor = Color(0xFF373D37)

        setContent {
            TuristicAppAmovTheme {
                // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ){
                        ResultsLayout(
                            LocalContext.current,
                            numCertas,
                            listaErradas,
                            activeUser,
                            startColor,
                            endColor
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


@Composable
fun ResultsLayout(
    context: Context,
    numCertas: Int,
    listaErradas: ArrayList<Question>,
    activeUser: User,
    startColor: Color,
    endColor: Color
) {

   Box( modifier = Modifier
       .fillMaxSize()
       .background(brush = Brush.linearGradient(colors = listOf(startColor, endColor)))){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(16.dp)
            ,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        // Display Round Progress Bar
        RoundProgressBar(
            percentage = getScorePercentage(numCertas, listaErradas.size).toDouble(),
            modifier = Modifier
                .size(150.dp)
                .padding(8.dp).fillMaxSize()

        )

        // put divider here //

        // Display List of Wrong Questions
        if (listaErradas.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .fillMaxWidth()
            ) {
                itemsIndexed(listaErradas) { index, question ->
                    // Wrong Question
                    Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.fillMaxWidth().padding(top = 10.dp))
                    MyQuestion(listaQuestions = listaErradas , index)

                    // Wrong Options
                    question.listOpt?.forEach {
                        MyResultsOption(opt = it)
                    }

                }
            }
        } else {
            Text(
                text = "Congratulations! All answers are correct.",
                modifier = Modifier.padding(8.dp)
            )
        }

        // Button to Go Back
        Button(
            onClick = {
                val intent = Intent(context, MenuActivity::class.java)
                intent.putExtra("USER", activeUser)
                context.startActivity(intent)

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(text = "Main menu")
        }
    }
   }
}



fun getScorePercentage(numCertas: Int, size: Int): String {
    return ((numCertas.toDouble() / size) * 100).roundToLong().toString()
}




@Preview
@Composable
fun Te() {


    val opts1 = ParOptionsAnswers("test_opts1", answers = true, selected = true)
    val opts2 = ParOptionsAnswers("test_opts12", answers = true, selected = false)
    val opts3 = ParOptionsAnswers("test_opts13", answers = true, selected = false)
    val opts4 = ParOptionsAnswers("test_opts14", answers = true, selected = false)

    val listOpts = ArrayList<ParOptionsAnswers>()
    listOpts.add(opts1)
    listOpts.add(opts2)
    listOpts.add(opts3)
    listOpts.add(opts4)

    val quest = Question("Teste corpo da pergunta.. bla bla bla ", listOpt = listOpts, "explain", 1)
    val listaQuests = ArrayList<Question>()
    listaQuests.add(quest)
    listaQuests.add(quest)

    val us = User("Jaffar", "pwd")


    ResultsLayout(LocalContext.current,1,listaQuests,us,Color(0xFF44617E),Color(0xFF373D37))

}

