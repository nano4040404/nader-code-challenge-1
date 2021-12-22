package com.example.nadercodechallenge1.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nadercodechallenge1.databinding.FragmentNewsBinding
import com.example.nadercodechallenge1.di.AppModule
import com.example.nadercodechallenge1.ui.base.ScopedFragment
import com.example.nadercodechallenge1.ui.setVisibility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class NewsFragment : ScopedFragment() {

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>? =null

    private lateinit var viewModel: NewsViewModel
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = AppModule().getNewsViewModelFactory()

        viewModel = ViewModelProvider(this,viewModelFactory)[NewsViewModel::class.java]
        binding.newsLottieAnimationView.setVisibility(true)
        navController = Navigation.findNavController(view)
        bindUI()

        binding.NYItemsListRefresh.setOnRefreshListener {
            binding.newsLottieAnimationView.setVisibility(true)
            refreshUI()
        }
    }

    private fun bindUI()= launch(Dispatchers.Main){
        val currentArticle = viewModel.article.await()

        currentArticle.observe(viewLifecycleOwner, {
            if (it == null) return@observe

            binding.newsLottieAnimationView.setVisibility(false)
            layoutManager = LinearLayoutManager(requireContext())
            binding.newsRecyclerView.layoutManager = layoutManager
            adapter = NewsRecyclerAdapter(it,navController)
            binding.newsRecyclerView.adapter = adapter

        })
    }

    private fun refreshUI()= launch(Dispatchers.Main){
        val currentArticle = viewModel.article.await()

        currentArticle.observe(viewLifecycleOwner, {
            if (it == null) return@observe

            binding.newsLottieAnimationView.setVisibility(false)
            adapter = NewsRecyclerAdapter(it,navController)
            binding.newsRecyclerView.adapter = adapter
            binding.NYItemsListRefresh.isRefreshing = false

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}