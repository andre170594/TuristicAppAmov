package com.example.turisticappamov.screens

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.turisticappamov.ui.theme.*



@Composable
fun HomeScreen(
    user: User,
    intent: Intent,
    content: Context
) {


    // COLOR SCHEME
    val startColor = if (user.goDark == false) LightStartColor else DarkStartColor
    val endColor = if (user.goDark == false) LightEndColor else DarkEndColor
    val btnColor = if (user.goDark == false) LightBtnColor else DarkBtnColor
    val btnTextColor = if (user.goDark == false) LightBtnTextColor else DarkBtnTextColor
    val widgetBackColor = if (user.goDark == false) LightWidgetBackColor else DarkWidgetBackColor
    val textColor = if (user.goDark == false) LightTExtColor else DarkTExtColor
    val testDarkMode = user.goDark != false


    // Option picker state
    var selectedOption by remember { mutableStateOf("CAD") }
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("CAD", "CSA", "CIS-HR")


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
                .scale(2.0f)
                .padding(top = 0.dp, bottom = 6.dp)
                .fillMaxWidth(), colorFilter = ColorFilter.tint(textColor)
        )

      Text(
          text = "Devs on Tour",
          modifier = Modifier.padding(bottom = 10.dp, top = 20.dp), fontFamily = FontFamily.Monospace, color = textColor
      )



      //  AnimatedGreetingText("Devs on Tour",textColor)
        AnimatedGreetingText("Hi " + user.username.toString(),textColor)


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
                            .padding(8.dp),
                        textColor
                    )
                }
            }
        }



        // Option Picker Dropdown
        Text(
            text = "Select Certification",
            color = textColor,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Box {
            Text(
                text = selectedOption,
                color = textColor,
                fontSize = 14.sp,
                modifier = Modifier
                    .background(widgetBackColor, shape = RoundedCornerShape(8.dp))
                    .padding(12.dp)
                    .clickable { expanded = true }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach {option -> 
                    DropdownMenuItem(modifier = Modifier.background(widgetBackColor),
                        text = { Text(text = option, color = textColor)},
                        onClick = {
                            selectedOption = option
                            expanded = false
                        }
                    )
                }
            }
        }

        Button(
            onClick = { startTest(user, intent, content,testDarkMode,selectedOption) },
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
    user: User,
    intent: Intent,
    content: Context,
    testDarkMode: Boolean,
    selectedOption: String,
) {
    intent.putExtra("USER",user)
    intent.putExtra("GoDARK",testDarkMode)
    intent.putExtra("TESTTYPE",selectedOption)


    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(content,intent,null)
}


// ANIMS
@Composable
fun AnimatedGreetingText(texto: String, textColor: Color) {
    // State to control the animation trigger
    var isVisible by remember { mutableStateOf(false) }

    // Start animation when composable is first loaded
    LaunchedEffect(Unit) {
        isVisible = true
    }

    // Animate opacity (fade-in) and scale size
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )
    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.5f,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    Text(
        text = texto,
        fontSize = 24.sp,
        fontFamily = FontFamily.Monospace,
        color = textColor.copy(alpha = alpha),
        modifier = Modifier.scale(scale).padding(bottom = 20.dp, top = 20.dp)
    )

}


