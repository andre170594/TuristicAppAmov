package com.example.turisticappamov.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turisticappamov.mylayouts.MyFeedItem
import com.example.turisticappamov.mylayouts.MyFeedQuestionOpt
import com.example.turisticappamov.mymodels.FeedItem
import com.example.turisticappamov.mymodels.User
import com.example.turisticappamov.ui.theme.*


@Composable
fun ProfileScreen(
    user: User,
    listFeedItems: ArrayList<FeedItem>?,
    listNumQuestionExam: ArrayList<Int>
){

    // COLOR SCHEME
    val startColor = if (user.goDark == false) LightStartColor else DarkStartColor
    val endColor = if (user.goDark == false) LightEndColor else DarkEndColor
    val cardBackColor = if (user.goDark == false) LightFeedColor else DarkFeedColor
    val textColor = if (user.goDark == false) LightTExtColor else DarkTExtColor
    val optColor = if (user.goDark == false) LightCardColor else DarkCardColor

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colors = listOf(startColor, endColor)))
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Feed",
            fontFamily = FontFamily.Serif,
            fontSize = 24.sp,
            color = textColor
        )
        Spacer(modifier = Modifier.height(66.dp))

        LazyColumn(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
                .fillMaxWidth()
        ){
            if(listFeedItems!=null) {

                listFeedItems.sortBy { it.timestamp }
                listFeedItems.reverse()
                itemsIndexed(listFeedItems) { index, feedIt ->

                    MyFeedItem(feedIt,cardBackColor)
                    Divider(
                        color = Color.Transparent,
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Total question in Database",
            fontFamily = FontFamily.Monospace,
            fontSize = 18.sp,
            color = textColor,
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ){

            itemsIndexed(listNumQuestionExam) { index, feedIt ->
                var texto= "Searching..."
                when (index) {
                    0 -> texto = "CAD"
                    1 -> texto = "CIS-PMM"
                    2 -> texto = "CIS-HR"
                    3 -> texto = "CSA"
                    4 -> texto = "ITSM"
                }
                MyFeedQuestionOpt(text = texto, num = feedIt ,cardBackColor)

            }
        }
    }
}
