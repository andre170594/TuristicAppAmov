package com.example.turisticappamov.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turisticappamov.R.drawable
import com.example.turisticappamov.mylayouts.MySettingsOpt
import com.example.turisticappamov.mymodels.User

@Composable
fun SettingsScreen(user:User,listaSettings: ArrayList<MutableState<Boolean>>) {

    // DARK MODE
    var startColor = Color(0xFFCBD5A5)
    var endColor = Color(0xFFF3E9D4)
    var cardColor = Color(0xFF4E6C50)
    var btnColor = Color(0xFF4E6C50)
    var btnTextColor = Color(0xFFEEF5EE)
    var textColor = Color(0xFF617C63)
    if(user.goDark == true){
        startColor = Color(0xFF111644)
        endColor = Color(0xFF321B4F)
        cardColor = Color(0xFF321B4F)
        btnColor = Color(0xFFC87ABE)
        btnTextColor = Color(0xFFD7D0DB)
        textColor = Color(0xFFD7D0DB)
    }
    
    
    // PREPARE OPTS TO BE DISPLAYED
    val opt1 = "Dark Mode"
    val opt2 = "Notification"
    val opt3 = "Update Feed"
    val listaOps = ArrayList<String>()
    listaOps.add(opt1)
    listaOps.add(opt2)
    listaOps.add(opt3)
    // END PREPARE OPTS TO BE DISPLAYED

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colors = listOf(startColor, endColor))),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Settings",
            fontFamily = FontFamily.Serif,
            fontSize = 24.sp,
            color = textColor
        )
        Spacer(modifier = Modifier.height(66.dp))


        Image(
            painter = painterResource(id = drawable.empty),
            contentDescription = "User Image",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape), colorFilter = ColorFilter.tint(textColor)
        )
        Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${user.username}",
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp,
                color = textColor,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { },
            modifier = Modifier
                .padding(vertical = 2.dp, horizontal = 2.dp)
                .size(width = 180.dp, height = 38.dp)
                .clipToBounds(),
            colors = ButtonDefaults.buttonColors(btnColor),
            shape = RoundedCornerShape(100.dp),
        ) {
            Text(
                text = "Edit Photo",
                color = btnTextColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp),
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(66.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp), shape = RoundedCornerShape(22.dp)
        ) {
            Column(modifier = Modifier.background(cardColor)) {
                listaSettings.forEachIndexed{ index, settingState ->
                    MySettingsOpt(listaOps[index], Icons.Default.Settings, settingState
                    ) {
                        settingState.value = !settingState.value
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))

    }
}


