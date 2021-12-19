package com.rizwanamjadnov.newsforandroid.utils

import com.rizwanamjadnov.newsforandroid.models.News
import org.json.JSONArray
import org.json.JSONObject

class ResponseToModel {
    companion object{
        public fun newsModelList(data: JSONArray): List<News>{
            val newsData = mutableListOf<News>()
            for(i in 0 until data.length()){
                val current: JSONObject = data[i] as JSONObject
                newsData.add(News(current.getString("author"),
                    current.getString("title"),
                    current.getString("description"),
                    current.getString("url")
                ))
            }
            return newsData
        }
    }
}