package com.rizwanamjadnov.newsforandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rizwanamjadnov.newsforandroid.R
import com.rizwanamjadnov.newsforandroid.models.News

class NewsAdapter(
    private val data: List<News>
): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleListItemText: TextView = itemView.findViewById(R.id.titleListItemText)
        val descriptionListItemText: TextView = itemView.findViewById(R.id.descriptionListItemText)
        val authorListItemText: TextView = itemView.findViewById(R.id.authorListItemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_list_item, null, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = data[position]
        holder.apply {
            titleListItemText.text = newsItem.title
            descriptionListItemText.text = newsItem.description
            authorListItemText.text = newsItem.author
            if(newsItem.author == "null"){
                authorListItemText.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}