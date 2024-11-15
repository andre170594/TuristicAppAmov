package com.example.turisticappamov.mylayouts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
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
fun MyFeedQuestionOpt(text: String, num: Int, optColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 2.dp)
            .background(optColor, shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp))
        ,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Icon(
                imageVector = if(num> 0 ) Icons.Default.Check else Icons.Default.Clear,
                contentDescription = "optLogo",
                tint = Color.White,
                modifier = Modifier
                    .padding(start =  10.dp)
            )

            // Text in the middle
            Text(
                text = text,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp, 0.dp, 0.dp, 0.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Justify,
            )
            Text(
                text = num.toString(),
                modifier = Modifier
                    .weight(1f)
                    .padding(22.dp, 0.dp, 12.dp, 0.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.End,
            )

        }
    }
}