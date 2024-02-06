package com.example.turisticappamov.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.turisticappamov.R
import com.example.turisticappamov.mylayouts.RoundProgressBar
import com.example.turisticappamov.mymodels.User



@Composable
fun HomeScreen(user: User, intent: Intent, content: Context) {
    val startColor = Color(0xFF585069)
    val endColor = Color(0xFF1F1A2B)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colors = listOf(startColor, endColor)))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(3.0f)
                .padding(top = 2.dp, bottom = 6.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Devs on Tour",
            modifier = Modifier.padding(bottom = 10.dp, top = 20.dp), fontFamily = FontFamily.Monospace
        )
        Text(
            text = "Hi ${user.username}",
            modifier = Modifier.padding(bottom = 40.dp, top = 10.dp),
            fontSize = 24.sp, fontFamily = FontFamily.Monospace
        )
        // 1Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box( // LAST SCORE WIDGET
                modifier = Modifier
                    .size(150.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(16.dp))
                    .padding(8.dp)
            ) {
              Column(
                  modifier = Modifier
                      .fillMaxSize()
                      .padding(8.dp)
              ) {
                  Text(
                      text = "Last",
                      color = Color.LightGray,
                      modifier = Modifier
                          .fillMaxWidth()
                          .padding(horizontal = 20.dp)
                          .padding(bottom = 6.dp),
                      textAlign = TextAlign.Center,
                      fontSize = 14.sp, fontFamily = FontFamily.Monospace
                  )
                  RoundProgressBar(
                      percentage = getUserLastScore(user),
                      modifier = Modifier
                          .fillMaxSize()
                          .padding(8.dp)
                  )
              }



            }
            Box( // AVG SCORE WIDGET
                modifier = Modifier
                    .size(150.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(16.dp))
                    .padding(8.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Text(
                        text = "AVG",
                        color = Color.LightGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 6.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp, fontFamily = FontFamily.Monospace
                    )
                    RoundProgressBar(
                        percentage = getAvgScore(user),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )
                }
            }
        }
        Button(
            onClick = { startTest("teste01", user, intent, content) },
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .clipToBounds(),
            colors = ButtonDefaults.buttonColors(startColor),
            shape = RoundedCornerShape(100.dp),
        ) {
            Text(
                text = "Start",
                color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = 14.sp, fontFamily = FontFamily.Monospace
            )
        }
    }
}
fun getUserLastScore(user: User): Double {
    return if (user.lastScore!=null){
        user.lastScore!!
    } else
        0.0
}
fun getAvgScore(user: User): Double {
    val lista = user.getAvgScoresList()
    return if(!lista.isNullOrEmpty()){
        lista.average()
    }else
        0.0
}
fun startTest(selectedOPT: String, user: User, intent: Intent, content:Context) {
    intent.putExtra("USER",user)
    intent.putExtra("TEST",selectedOPT)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(content,intent,null)

}

