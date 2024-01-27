package com.example.turisticappamov.mylayouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBarNavigation(current: Int, total: Int, onBack: () -> Unit, onForward: () -> Unit) {
    val progress = (1 + current.toFloat()) / total.toFloat()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = Color.DarkGray,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { onBack() }, modifier = Modifier.align(Alignment.CenterVertically)) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(16.dp)
                        )
                )

                Text(
                    text = "${current + 1}/$total",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(8.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            IconButton(onClick = { onForward() }, modifier = Modifier.align(Alignment.CenterVertically)) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Forward")
            }
        }
    }
}