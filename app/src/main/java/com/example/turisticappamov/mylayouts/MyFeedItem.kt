package com.example.turisticappamov.mylayouts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.turisticappamov.mymodels.FeedItem

@Composable
fun MyFeedItem(feedIt: FeedItem) {
   

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon/Image on the left
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Timestamp Icon",
                tint = Color.White
            )

            // Text in the middle
            Text(
                text = " " + feedIt.nameUser + " " + feedIt.title + " " + feedIt.content,
                modifier = Modifier.weight(1f),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
            )

            // Timestamp on the right
            Text(
                text = feedIt.getFormatedDate().toString(), // Convert timestamp to string
                color = Color.White,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}



@Preview
@Composable
fun TestItem(){

    val feed = FeedItem("Acheived", "70%",1234567890L,"Jaffar")
    MyFeedItem(feedIt = feed)
    
}