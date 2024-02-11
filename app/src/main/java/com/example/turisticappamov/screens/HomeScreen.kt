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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
fun HomeScreen(user: User, intent: Intent, content: Context,listaSettings: ArrayList<MutableState<Boolean>>) {
    // DARK MODE
    var testDarkMode = false
    var startColor = Color(0xFFCBD5A5)
    var endColor = Color(0xFFF3E9D4)
    var textColor = Color(0xFF617C63)
    var widgetBackColor = Color(0xFFFEFAE8)
    var btnColor = Color(0xFF4E6C50)
    var btnTextColor = Color(0xFFDCE6DC)
    if(user.goDark == true){
        startColor = Color(0xFF111644)
        endColor = Color(0xFF321B4F)
        textColor = Color(0xFF9FA8DA)
        widgetBackColor = Color(0xFF45256D)
        btnColor = Color(0xFFC87ABE)
        btnTextColor = Color(0xFFD7D0DB)
        testDarkMode = true
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colors = listOf(startColor, endColor)))
            .padding(top = 0.dp, start = 24.dp, end = 24.dp, bottom = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(3.0f)
                .padding(top = 0.dp, bottom = 6.dp)
                .fillMaxWidth(), colorFilter = ColorFilter.tint(textColor)
        )
        Text(
            text = "Devs on Tour",
            modifier = Modifier.padding(bottom = 10.dp, top = 20.dp), fontFamily = FontFamily.Monospace, color = textColor
        )
        Text(
            text = "Hi ${user.username}",
            modifier = Modifier.padding(bottom = 40.dp, top = 20.dp),
            fontSize = 24.sp, fontFamily = FontFamily.Monospace, color = textColor
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
                    .background(color = widgetBackColor, shape = RoundedCornerShape(16.dp))
                    .padding(8.dp)
            ) {
              Column(
                  modifier = Modifier
                      .fillMaxSize()
                      .padding(8.dp)
              ) {
                  Text(
                      text = "Last",
                      color = textColor,
                      modifier = Modifier
                          .fillMaxWidth()
                          .padding(horizontal = 20.dp)
                          .padding(bottom = 6.dp),
                      textAlign = TextAlign.Center,
                      fontSize = 16.sp, fontFamily = FontFamily.Monospace
                  )
                  RoundProgressBar(
                      percentage = getUserLastScore(user),
                      modifier = Modifier
                          .fillMaxSize()
                          .padding(8.dp),textColor
                  )
              }
            }
            Box( // AVG SCORE WIDGET
                modifier = Modifier
                    .size(150.dp)
                    .background(widgetBackColor, shape = RoundedCornerShape(16.dp))
                    .padding(8.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Text(
                        text = "AVG",
                        color = textColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 6.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp, fontFamily = FontFamily.Monospace
                    )
                    RoundProgressBar(
                        percentage = getAvgScore(user),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),textColor
                    )
                }
            }
        }
        Button(
            onClick = { startTest("teste01", user, intent, content,testDarkMode) },
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .clipToBounds(),
            colors = ButtonDefaults.buttonColors(btnColor),
            shape = RoundedCornerShape(100.dp),
        ) {
            Text(
                text = "Start",
                color = btnTextColor,
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
fun startTest(
    selectedOPT: String,
    user: User,
    intent: Intent,
    content: Context,
    testDarkMode: Boolean
) {
    intent.putExtra("USER",user)
    intent.putExtra("TEST",selectedOPT)
    intent.putExtra("GoDARK",testDarkMode)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(content,intent,null)

}

