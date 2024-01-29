package com.example.turisticappamov.mylayouts

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.turisticappamov.mymodels.User
import com.example.turisticappamov.screens.HomeScreen

@Composable
fun ProgressBarNavigation(current: Int, total: Int, onBack: () -> Unit, onForward: () -> Unit) {
    val progress = (1 + current.toFloat()) / total.toFloat()

    val colorBox = Color(0x8068598A)


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = colorBox,
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
                    .fillMaxWidth().padding(top = 20.dp)
            ) {
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    trackColor = Color(0xFFC5BFCE),
                    color = Color(0xFF02181F)
                )

                Text(
                    text = "${current + 1}/$total",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(8.dp),
                    color = Color(0xFF02181F),
                    fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace
                )
            }

            IconButton(onClick = { onForward() }, modifier = Modifier.align(Alignment.CenterVertically)) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Forward")
            }
        }
    }
}

@Preview
@Composable
fun Test(){
    ProgressBarNavigation(1,10,{},{})
}