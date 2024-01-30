package com.example.turisticappamov.mylayouts

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turisticappamov.mymodels.ParOptionsAnswers

@Composable
fun MyResultsOption(opt: ParOptionsAnswers) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        val colorSelected = Color(0xE9563B5C)
        val colorNotSelected = Color(0xEBA699AF)

        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = findContainerColor(opt),
                    shape = RoundedCornerShape(16.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (opt.selected == true) colorSelected else colorNotSelected,
                contentColor = if (opt.selected == true) colorNotSelected else colorSelected
            ),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = opt.option.toString(),
                color = if (opt.selected == true) Color.White else Color.DarkGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
        }
    }
}

fun findContainerColor(opt: ParOptionsAnswers): Color {
    return if (opt.answers == true) {
        Color.Green
    } else {
        Color.Red
    }
}


@Preview
@Composable
fun Testeded() {

    val par = ParOptionsAnswers("isto é uma resposta",true,true)
    MyResultsOption(par)
}
