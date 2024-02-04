package com.example.turisticappamov.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turisticappamov.mylayouts.MyFeedItem
import com.example.turisticappamov.mylayouts.MyQuestion
import com.example.turisticappamov.mylayouts.MyResultsOption
import com.example.turisticappamov.mymodels.FeedItem
import java.sql.Timestamp
import java.time.Instant

@Composable
fun ProfileScreen(listFeedItems: ArrayList<FeedItem>?){
    val startColor = Color(0xFF3A3034)
    val endColor = Color(0xFF787380)




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colors = listOf(startColor, endColor)))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                    )
                }
            }
        }

    }

}


@Preview
@Composable
fun T1(){

    val feed = FeedItem("achieved", "70%",1234567890L,"Jaffar")
    val listFeeds = ArrayList<FeedItem>()
    listFeeds.add(feed)
    listFeeds.add(feed)
    listFeeds.add(feed)
    listFeeds.add(feed)
    ProfileScreen(listFeeds)
}