package com.rizwanamjadnov.newsforandroid.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rizwanamjadnov.newsforandroid.R
import com.rizwanamjadnov.newsforandroid.models.News
import com.squareup.picasso.Picasso

class NewsAdapter(
    private val data: List<News>
): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleListItemText: TextView = itemView.findViewById(R.id.titleListItemText)
        val descriptionListItemText: TextView = itemView.findViewById(R.id.descriptionListItemText)
        val authorListItemText: TextView = itemView.findViewById(R.id.authorListItemText)
        val newsItemImage: ImageView = itemView.findViewById(R.id.newsItemImage)
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

            Picasso.get().load(newsItem.image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(newsItemImage)
            if(newsItem.author == "null"){
                authorListItemText.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}