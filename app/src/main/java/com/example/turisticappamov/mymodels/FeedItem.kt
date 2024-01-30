package com.example.turisticappamov.mymodels

import java.io.Serializable

class FeedItem( val title: String,
                val content: String,
                val timestamp: Long,
                val nameUser: String
                ):Serializable
{

}