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
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turisticappamov.mylayouts.MyFeedItem
import com.example.turisticappamov.mymodels.FeedItem
import com.example.turisticappamov.mymodels.User
import com.example.turisticappamov.ui.theme.DarkEndColor
import com.example.turisticappamov.ui.theme.DarkStartColor
import com.example.turisticappamov.ui.theme.LightEndColor
import com.example.turisticappamov.ui.theme.LightStartColor

@Composable
fun ProfileScreen(user: User, listFeedItems: ArrayList<FeedItem>?){

    // COLOR SCHEME
    val startColor = if (user.goDark == false) LightStartColor else DarkStartColor
    val endColor = if (user.goDark == false) LightEndColor else DarkEndColor

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colors = listOf(startColor, endColor)))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Feed",
            fontFamily = FontFamily.Serif,
            fontSize = 24.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(66.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .fillMaxWidth()
        ){
            if(listFeedItems!=null) {

                listFeedItems.sortBy { it.timestamp }
                itemsIndexed(listFeedItems) { index, feedIt ->
                    // Wrong Question

                    MyFeedItem(feedIt)
                    Divider(
                        color = Color.Transparent,
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                    )
                }
            }
        }

    }
}
