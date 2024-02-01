package com.example.turisticappamov.mylayouts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun RoundProgressBar(percentage: Double, modifier: Modifier = Modifier) {


    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val outerRadius = size.minDimension / 2
            val strokeWidth = 20f

            // Draw the background circle
            drawArc(
                color = Color.Gray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(strokeWidth)
            )

            // Draw the progress arc
            drawArc(
                color = if (percentage >= 70) Color.Green else Color.Blue,
                startAngle = -90f,
                sweepAngle = ((percentage/100f)*360f).toFloat(),
                useCenter = false,
                style = Stroke(strokeWidth)
            )
        }

        Text(
            text = "${percentage.roundToInt()}%",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}