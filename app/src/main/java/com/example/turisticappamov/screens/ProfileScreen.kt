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
import androidx.compose.foundation.lazy.LazyListState
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

@Composable
fun ProfileScreen(user: User, listFeedItems: ArrayList<FeedItem>?, lazyListState: LazyListState){

    // DARK MODE
    var startColor = Color(0xFFCBD5A5)
    var endColor = Color(0xFFF3E9D4)
    if(user.goDark == true){
        startColor = Color(0xFF111644)
        endColor = Color(0xFF321B4F)
    }

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
