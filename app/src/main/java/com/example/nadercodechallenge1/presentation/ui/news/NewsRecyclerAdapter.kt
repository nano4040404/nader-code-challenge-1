package com.example.nadercodechallenge1.presentation.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nadercodechallenge1.R
import com.example.nadercodechallenge1.data.db.current.CurrentArticleEntry
import com.example.nadercodechallenge1.databinding.CurrentArticleListBinding


class NewsRecyclerAdapter(
    private val allArticles: List<CurrentArticleEntry>,
    var navController: NavController
): RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CurrentArticleListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(allArticles[position]) {
                binding.articleTitle.text = title
                binding.articleAbstract.text = abstract
                binding.articleTopic.text = type
                binding.articleUpdate.text = updated
                itemView.setOnClickListener {
                    val bundle = bundleOf("url" to url)
                    navController.navigate(R.id.action_newsFragment_to_articleLinkFragment, bundle)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return allArticles.size
    }

    inner class ViewHolder(val binding: CurrentArticleListBinding):RecyclerView.ViewHolder(binding.root)

}