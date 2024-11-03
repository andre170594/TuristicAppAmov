package com.example.turisticappamov.myactivities


import android.content.Context
import android.content.Intent
import com.example.turisticappamov.mylayouts.MyOption
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.turisticappamov.mymodels.ParOptionsAnswers
import com.example.turisticappamov.mymodels.Question
import com.example.turisticappamov.mymodels.User
import com.example.turisticappamov.mylayouts.MyQuestion
import com.example.turisticappamov.mylayouts.ProgressBarNavigation
import com.example.turisticappamov.ui.theme.TuristicAppAmovTheme
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

@Suppress("NAME_SHADOWING")
class TesteActivity : ComponentActivity() {

    // CAD EXAMS


    private lateinit var listaQuestions: ArrayList<Question>
    private var totalQuestions: Int = 0
    private lateinit var database: FirebaseDatabase
    private lateinit var questionsRef: DatabaseReference
    private lateinit var activeUser: User
    private var globalSelectedCount by mutableIntStateOf(0)
    private var prog  by mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goFullScreen()

        val goDark = intent.getBooleanExtra("GoDARK",false)


        initGame({
            setContent {
                TuristicAppAmovTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        TestLayout(
                            listaQuestions,
                            activeUser,
                            prog,
                            50,
                            globalSelectedCount,
                            onGlobalSelectedCountChange = { newCount -> globalSelectedCount = newCount},
                            onBack = { goBack() },
                            onForward = { goForward() }, LocalContext.current,
                            goDark

                        )
                    }
                }
            }
        }, "CAD")
    }

    private fun goFullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
    private fun initGame(callback: (ArrayList<Question>) -> Unit,testType: String) {
        activeUser = intent.getSerializableExtra("USER") as? User ?: User()
        val testType = intent.getStringExtra("TESTTYPE")
        val pathToQuestions = "questions/$testType"

        listaQuestions = ArrayList()

        lifecycleScope.launch {
            try {
                // Initialize Firebase
                database = FirebaseDatabase.getInstance()
                questionsRef = database.getReference(pathToQuestions)
                // Fetch questions from Firebase
                val questionList = withContext(Dispatchers.IO) {
                    val dataSnapshot = questionsRef.get().await()
                    val list = ArrayList<Question>()
                    for (questionSnapshot in dataSnapshot.children) {
                        val pergunta = questionSnapshot.child("pergunta").getValue(String::class.java)
                        val listOpt = questionSnapshot.child("listOpt").getValue(object :
                            GenericTypeIndicator<ArrayList<ParOptionsAnswers>>() {})
                        val explanation = questionSnapshot.child("explanation").getValue(String::class.java)
                        val numCorrect = questionSnapshot.child("numCorrect").getValue(Int::class.java)

                        val question = Question(
                            pergunta,
                            listOpt,
                            explanation,
                            numCorrect ?: 0
                        )
                        list.add(question)
                    }
                    list.shuffle()
                    for (quest in list) {
                        quest.listOpt?.shuffle()
                    }
                    list
                }
                listaQuestions = questionList
                totalQuestions = listaQuestions.size

                // Invoke the callback with the fetched data
                callback(listaQuestions)

            } catch (e: Exception) {
                // Handle errors here
                e.printStackTrace()
            }
        }
    }
    private fun goBack() {
        if (prog > 0) {
            prog--
            globalSelectedCount = getNumSelected(prog)
        }
    }
    private fun goForward() {
        if (prog < totalQuestions - 1) {
            prog++
            globalSelectedCount = getNumSelected(prog)
        }
    }
    private fun getNumSelected(prog: Int): Int {
        var res = 0
        val listOpt = listaQuestions[prog].listOpt
        listOpt?.forEach {
            if(it.selected == true)
                res++
        }
        return res
    }
}

@Composable
fun TestLayout(
    listaQuestions: ArrayList<Question>,
    activeUser: User,
    prog: Int,
    numTotal: Int,
    globalSelectedCount: Int,
    onGlobalSelectedCountChange: (Int) -> Unit,
    onBack: () -> Unit,
    onForward: () -> Unit, context: Context,
    goDark: Boolean
) {

    // DARK MODE
    var startColor = Color(0xFFCBD5A5)
    var endColor = Color(0xFFF3E9D4)
    var btnColor = Color(0xFF4E6C50)
    var btnTextColor = Color(0xFFDCE6DC)
    if(goDark){
        startColor = Color(0xFF111644)
        endColor = Color(0xFF321B4F)
        btnColor = Color(0xFFC87ABE)
        btnTextColor = Color(0xFFD7D0DB)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(brush = Brush.linearGradient(colors = listOf(startColor, endColor)))
            .padding(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Adjust space between items if desired
        ) {
            item {
                ProgressBarNavigation(
                    current = prog,
                    total = numTotal,
                    onBack = { onBack() },
                    onForward = { onForward() },
                    textColor = btnTextColor,
                    backgroundColor = btnColor
                )
            }

            item {
                MyQuestion(listaQuestions, prog)
            }
            // Display options as separate items in the LazyColumn
            val listOpt = listaQuestions[prog].listOpt
            items(listOpt!!.size) { index ->
                MyOption(
                    listOpt[index],
                    listaQuestions[prog].numCorrect,
                    globalSelectedCount
                ) { newCount -> onGlobalSelectedCountChange(newCount) }
            }

            // Conditional Submit Button at the end
            if (prog == listaQuestions.size - 1) {
                item {
                    Button(
                        onClick = { submitAnswers(listaQuestions, activeUser, context) },
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 20.dp)
                            .clipToBounds(),
                        colors = ButtonDefaults.buttonColors(btnColor),
                        shape = RoundedCornerShape(100.dp),
                    ) {
                        Text(
                            text = "SUBMIT",
                            color = Color.LightGray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}
fun submitAnswers(listaQuestions: ArrayList<Question>, activeUser: User, context: Context) {
    // get wrong questions
    val wrongQuestions = getWrongQuestions(listaQuestions)
    val numCertas = listaQuestions.size - wrongQuestions.size

    val intent = Intent(context, ResultsActivity::class.java)
    intent.putExtra("WRONG_QUESTIONS", wrongQuestions)
    intent.putExtra("NUM_CERTAS", numCertas)
    intent.putExtra("TOTALQ", listaQuestions.size)
    intent.putExtra("USER", activeUser)
    context.startActivity(intent)
}
fun getWrongQuestions(listaQuestions: ArrayList<Question>): ArrayList<Question> {
    val wrongQuestions = ArrayList<Question>()

    for (question in listaQuestions) {
        // Check if all selected options are correct
        if (!areAllOptionsCorrect(question.listOpt)) {
            wrongQuestions.add(question)
        }
    }
    return wrongQuestions
}
fun areAllOptionsCorrect(listOpt: ArrayList<ParOptionsAnswers>?): Boolean {
    listOpt?.let {
        for (option in it) {
            // Check if the selected option is correct
            if (option.selected == true && option.answers != true) {
                return false
            } else if (option.selected != true && option.answers == true) {
                return false
            }
        }
        return true
    }
    return false
}


