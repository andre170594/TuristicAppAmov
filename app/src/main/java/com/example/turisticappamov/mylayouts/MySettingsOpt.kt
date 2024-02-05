package com.example.turisticappamov.mylayouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MySettingsOpt(text: String, icon: ImageVector, onClick: () -> Unit,isToggled:Boolean) {




    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            ,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon/Image on the left
            Icon(
                imageVector = icon,
                contentDescription = "optLogo",
                tint = Color.White, modifier = Modifier.padding(start =  8.dp)
            )

            // Text in the middle
            Text(
                text = "Nigh Mode",
                modifier = Modifier
                    .weight(1f)
                    .padding(22.dp, 0.dp, 0.dp, 0.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Left,
            )

            // toggle option
            Switch(
                checked = isToggled,
                onCheckedChange = {onClick()},
                modifier = Modifier.padding(end = 8.dp),
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFFC6B9DF),
                    checkedTrackColor = Color(0xFFE5E5E5),
                    checkedIconColor = Color(0xFF3C2C5C),
                    uncheckedThumbColor = Color(0xFFC6B9DF),
                    uncheckedTrackColor = Color(0xFFE5E5E5),
                    uncheckedIconColor = Color(0xFF3C2C5C)
                )

            )
        }
    }
}

@Preview
@Composable
fun T3(){
    MySettingsOpt("Dark Mode",Icons.Default.Person,{},false)
}