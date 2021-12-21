package com.example.nadercodechallenge1.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nadercodechallenge1.R
import com.example.nadercodechallenge1.data.db.current.CurrentArticleEntry


class NewsRecyclerAdapter(
    private val allArticles: List<CurrentArticleEntry>,
    var navController: NavController
): RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.current_article_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = allArticles.get(position)
        holder.articleTitle.text = article.title
        holder.articleAbstract.text = article.abstract
        holder.articleTopic.text = article.type
        holder.articleUpdate.text = article.updated
    }

    override fun getItemCount(): Int {
        return allArticles.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var articleTitle: TextView
        var articleAbstract: TextView
        var articleTopic: TextView
        var articleUpdate: TextView
        init {
            articleTitle = itemView.findViewById(R.id.articleTitle)
            articleAbstract = itemView.findViewById(R.id.articleAbstract)
            articleTopic = itemView.findViewById(R.id.articleTopic)
            articleUpdate = itemView.findViewById(R.id.articleUpdate)

            itemView.setOnClickListener{
                val position: Int = absoluteAdapterPosition


                val bundle = bundleOf("url" to allArticles.get(position).url)
                navController.navigate(R.id.action_newsFragment_to_articleLinkFragment,bundle)

            }

        }

    }

}