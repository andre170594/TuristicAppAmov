package com.example.turisticappamov.mymodels

import java.io.Serializable

class FeedItem( val title: String? = null,
                val content: String?= null,
                val timestamp: Long?= null,
                val nameUser: String?= null
                ):Serializable
{

}