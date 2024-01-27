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
fun ProfileScreen(){
    val startColor = Color(0xFF3A3034)
    val endColor = Color(0xFF787380)
    Box(
        modifier = Modifier
            .fillMaxSize().background(brush = Brush.linearGradient(colors = listOf(startColor, endColor))),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Feed page",
            fontFamily = FontFamily.Serif,
            fontSize = 22.sp
        )
    }

}