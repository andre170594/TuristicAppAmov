package com.example.turisticappamov.mylayouts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyButton(
     texto: String
){
    Button(onClick = { },
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clipToBounds(),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(100.dp),
    ) {
        Text(
            text = texto,
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }

}
@Preview(showBackground = true)
@Composable
fun MyButtonPrev() {
    MyButton(texto = "test")
}