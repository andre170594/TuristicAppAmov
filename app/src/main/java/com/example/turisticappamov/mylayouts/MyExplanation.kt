package com.example.turisticappamov.mylayouts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyExplanation(exp: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            ),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth().background(Color(0xFFA5D6A7), RoundedCornerShape(20.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

                    Text(
                        text = exp,
                        modifier = Modifier.padding(20.dp),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Center
                    )


        }
    }
}