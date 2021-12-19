package com.rizwanamjadnov.newsforandroid

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.rizwanamjadnov.newsforandroid.adapters.NewsAdapter
import com.rizwanamjadnov.newsforandroid.models.News
import com.rizwanamjadnov.newsforandroid.utils.ResponseToModel

class NewsFragment : Fragment() {
    companion object{
        private const val TAG = "NewsFragment"
        private const val NEWS_ENDPOINT =
            "http://newsapi.org/v2/top-headlines?country=in&apiKey=a4c9a498a2da4bcf9bf9839cbc94120a"
    }

    private lateinit var requestQueue: RequestQueue

    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var loadingNewsText: TextView

    private var newsData = mutableListOf<News>()
    private var newsAdapter = NewsAdapter(newsData)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        requestQueue = Volley.newRequestQueue(requireContext())

        newsRecyclerView = view.findViewById(R.id.newsRecyclerView)
        loadingNewsText = view.findViewById(R.id.loadingNewsText)

        newsRecyclerView.adapter = newsAdapter
        newsRecyclerView.apply {
            val layoutManager = LinearLayoutManager(requireContext())
            newsRecyclerView.layoutManager = layoutManager
            addItemDecoration(
                DividerItemDecoration(
                    newsRecyclerView.context,
                    layoutManager.orientation
                )
            )
        }


        manageVisibilities()

        // sending the request
        getNewsFromServer()

        return view
    }

    private fun getNewsFromServer(){
        val newsRequest = object: JsonObjectRequest(
            Method.GET,
            NEWS_ENDPOINT,
            null,
            {
                var data = it.getJSONArray("articles")
                val newsData = ResponseToModel.newsModelList(data)
                this.newsData.addAll(newsData)
                newsAdapter.notifyDataSetChanged()
                manageVisibilities()
            },
            {
                Toast.makeText(requireContext(), "Unable to Load News", Toast.LENGTH_SHORT).show()
            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        // add the request to queue
        requestQueue.add(newsRequest)
    }
    private fun manageVisibilities(){
        if(newsData.size>0){
            newsRecyclerView.visibility = View.VISIBLE
            loadingNewsText.visibility = View.GONE
        }
        else{
            newsRecyclerView.visibility = View.GONE
            loadingNewsText.visibility = View.VISIBLE
        }
    }
}