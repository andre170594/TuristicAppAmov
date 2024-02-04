package com.example.turisticappamov.mymodels

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FeedItem( val title: String? = null,
                val content: String?= null,
                val timestamp: Long?= null,
                val nameUser: String?= null
                ):Serializable
{
    fun getFormatedDate():String?{
        timestamp?.let {
            val date = Date(it)
            val format = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
            return format.format(date)
        }
        return null
    }
}