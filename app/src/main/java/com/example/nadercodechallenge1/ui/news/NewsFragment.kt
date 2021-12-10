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
import com.example.nadercodechallenge1.R
import com.example.nadercodechallenge1.data.network.ConnectivityInterceptorImpl
import com.example.nadercodechallenge1.data.network.NYApiService
import com.example.nadercodechallenge1.data.network.NYTimesDataSourceImpl
import com.example.nadercodechallenge1.databinding.FragmentMoreBinding
import com.example.nadercodechallenge1.databinding.FragmentNewsBinding
import com.example.nadercodechallenge1.ui.base.ScopedFragment
import com.example.nadercodechallenge1.ui.setVisibility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class NewsFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: NewsViewModelFactory by instance()

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
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this,viewModelFactory).get(NewsViewModel::class.java)
        binding.newsLottieAnimationView.setVisibility(true)
        navController = Navigation.findNavController(view)
        bindUI()



//        val nyApi = NYApiService(ConnectivityInterceptorImpl(this.requireContext()))
//        val dataSource = NYTimesDataSourceImpl(nyApi)
//        dataSource.downloadedCurrentArticle.observe(viewLifecycleOwner,{
//            layoutManager = LinearLayoutManager(this.requireContext())
//            binding.newsRecyclerView.layoutManager = layoutManager
//
//            adapter = NewsRecyclerAdapter(it)
//            binding.newsRecyclerView.adapter = adapter
//
//
//        })
//        GlobalScope.launch(Dispatchers.Main) {
//            val test = dataSource.fetchCurrentArticle("emailed",7)
//        }



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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}