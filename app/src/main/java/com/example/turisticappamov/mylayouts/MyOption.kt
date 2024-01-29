package com.example.turisticappamov.mylayouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turisticappamov.mymodels.ParOptionsAnswers



@Composable
fun MyOption(
    opt: ParOptionsAnswers,
    numCorrect: Int,
    globalSelectedCount: Int,
    onGlobalSelectedCountChange: (Int) -> Unit
) {


    val colorSelected = Color(0xE9563B5C)
    val colorNotSelected = Color(0xEBA699AF)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Button(
            onClick = {
                if (opt.selected == true) {
                    // If the option is already selected, deselect it
                    opt.selected = false
                    onGlobalSelectedCountChange(globalSelectedCount - 1)
                } else if (globalSelectedCount < numCorrect) {
                    // If the maximum number of options is not reached, select the option
                    opt.selected = true
                    onGlobalSelectedCountChange(globalSelectedCount + 1)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clipToBounds(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (opt.selected== true) colorSelected else colorNotSelected,
                contentColor = if (opt.selected== true) colorNotSelected else colorSelected
            ),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = opt.option.toString(),
                color = if (opt.selected== true) Color.White else Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold

            )
        }
    }
}
@Preview
@Composable
fun Tested() {

    val par = ParOptionsAnswers("isto é uma resposta",false,false)
    MyOption(par,1,1,{})
}
