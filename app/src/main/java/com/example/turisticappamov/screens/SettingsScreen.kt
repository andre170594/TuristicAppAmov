package com.example.turisticappamov.screens

import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turisticappamov.mylayouts.MyFeedItem
import com.example.turisticappamov.mylayouts.MySettingsOpt
import com.example.turisticappamov.mymodels.FeedItem


@Composable
fun SettingsScreen() {
    val startColor = Color(0xFF411550)
    val cardColor = Color(0xFF321B4F)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(startColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = "Settings",
            fontFamily = FontFamily.Serif,
            fontSize = 28.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(66.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp), shape = RoundedCornerShape(22.dp)
        ) {
            Column(modifier = Modifier.background(cardColor)) {
                SettingsItem(
                    text = "General",
                    icon = Icons.Default.Person,
                    onClick = { /* Handle language settings */ }
                )
                Divider( modifier = Modifier.fillMaxWidth())
                SettingsItem(
                    text = "Theme",
                    icon = Icons.Default.Settings,
                    onClick = { /* Handle theme settings */ }
                )
                Divider( modifier = Modifier.fillMaxWidth())
                SettingsItem(
                    text = "Notifications",
                    icon = Icons.Default.Settings,
                    onClick = { /* Handle theme settings */ }
                )

            }
        }


        Spacer(modifier = Modifier.height(6.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp), shape = RoundedCornerShape(22.dp)
        ) {
            Column(modifier = Modifier.background(cardColor)) {
                SettingsItem(
                    text = "General",
                    icon = Icons.Default.Person,
                    onClick = { /* Handle language settings */ }
                )
                Divider( modifier = Modifier.fillMaxWidth().padding(top = 2.dp, bottom = 2.dp))
                SettingsItem(
                    text = "Theme",
                    icon = Icons.Default.Settings,
                    onClick = { /* Handle theme settings */ }
                )

            }
        }
        
    }
}

@Composable
fun SettingsItem(text: String, icon: ImageVector, onClick: () -> Unit) {
    //MyFeedItem(feedIt = FeedItem("test","t2",System.currentTimeMillis(),"jaffar"))

    MySettingsOpt(text,icon,onClick,false)
}



@Preview
@Composable
fun T2(){
    SettingsScreen()

}