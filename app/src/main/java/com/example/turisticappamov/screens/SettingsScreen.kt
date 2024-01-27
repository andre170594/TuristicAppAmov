package com.example.turisticappamov.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(){
    val startColor = Color(0xFF609BAA)
    val endColor = Color(0xFF495C46)
    Box(
        modifier = Modifier
            .fillMaxSize().background(brush = Brush.linearGradient(colors = listOf(startColor, endColor))),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "SettingsScreen",
            fontFamily = FontFamily.Serif,
            fontSize = 22.sp
        )
    }

}