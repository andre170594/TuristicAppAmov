package com.example.turisticappamov.screens
import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turisticappamov.R.drawable
import com.example.turisticappamov.myactivities.MenuActivity
import com.example.turisticappamov.mylayouts.MySettingsOpt
import com.example.turisticappamov.mymodels.User
import com.example.turisticappamov.ui.theme.*
import com.google.firebase.Firebase
import com.google.firebase.database.database


@Composable
fun SettingsScreen(user:User,listaSettings: ArrayList<MutableState<Boolean>>,menuActivity: MenuActivity) {

    // COLOR SCHEME
    val startColor = if (user.goDark == false) LightStartColor else DarkStartColor
    val endColor = if (user.goDark == false) LightEndColor else DarkEndColor
    val btnColor = if (user.goDark == false) LightBtnColor else DarkBtnColor
    val btnTextColor = if (user.goDark == false) LightBtnTextColor else DarkBtnTextColor
    val cardColor = if (user.goDark == false) LightCardColor else DarkCardColor
    val textColor = if (user.goDark == false) LightTExtColor else DarkTExtColor

    // PREPARE OPTS TO BE DISPLAYED
    val opt1 = "Dark Mode"
    val opt2 = "Notification"
    val opt3 = "Update Feed"
    val listaOps = ArrayList<String>()
    listaOps.add(opt1)
    listaOps.add(opt2)
    listaOps.add(opt3)
    // END PREPARE OPTS TO BE DISPLAYED

    // CHANGE USER IMAGE

    // Change number of questions per exam
  //  var numQuestionsExam by remember { mutableIntStateOf(50) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colors = listOf(startColor, endColor)))
            .verticalScroll(rememberScrollState()),
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
        Button(onClick = {},
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

        Button(onClick = { resetScores(user,menuActivity)},
            modifier = Modifier
                .padding(vertical = 2.dp, horizontal = 2.dp)
                .size(width = 200.dp, height = 38.dp)
                .clipToBounds(),
            colors = ButtonDefaults.buttonColors(btnColor),
            shape = RoundedCornerShape(100.dp),
        ) {
            Text(
                text = "Reset Scores",
                color = btnTextColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp),
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        Button(onClick = { clearCredentials(menuActivity)},
            modifier = Modifier
                .padding(vertical = 2.dp, horizontal = 2.dp)
                .size(width = 200.dp, height = 38.dp)
                .clipToBounds(),
            colors = ButtonDefaults.buttonColors(btnColor),
            shape = RoundedCornerShape(100.dp),
        ) {
            Text(
                text = "Logout",
                color = btnTextColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp),
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}


fun resetScores(user: User, menuActivity: MenuActivity) {

    user.avgScores = ArrayList()
    user.lastScore = 0.0
    val usersRef = Firebase.database.reference.child("users")
    val userRef = usersRef.child(user.userID.toString())
    userRef.child("avgScores").setValue(user.avgScores)
    userRef.child("lastScore").setValue(user.lastScore)

    Toast.makeText(menuActivity, "Scores Reset successfully", Toast.LENGTH_SHORT).show()


}

fun clearCredentials(menuActivity: MenuActivity) {
    val sharedPref = menuActivity.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        clear()
        apply()
    }
     menuActivity.finishAffinity()
}
