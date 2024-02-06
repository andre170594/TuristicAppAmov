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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turisticappamov.R.drawable
import com.example.turisticappamov.mylayouts.MySettingsOpt
import com.example.turisticappamov.mymodels.User

@Composable
fun SettingsScreen(user:User,listaSettings: ArrayList<MutableState<Boolean>>) {
    val startColor = Color(0xFF111644)
    val cardColor = Color(0xFF321B4F)

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
            .background(startColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Settings",
            fontFamily = FontFamily.Serif,
            fontSize = 24.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(66.dp))


        Image(
            painter = painterResource(id = drawable.empty),
            contentDescription = "User Image",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${user.username}",
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { },
            modifier = Modifier
                .padding(vertical = 2.dp, horizontal = 2.dp)
                .size(width = 180.dp, height = 38.dp)
                .clipToBounds(),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(100.dp),
        ) {
            Text(
                text = "Edit Photo",
                color = Color.LightGray,
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

@Preview
@Composable
fun T2(){
    var lista = ArrayList<MutableState<Boolean>>()
    SettingsScreen(User("Balbs","Jaffar Akbar"),lista)

}