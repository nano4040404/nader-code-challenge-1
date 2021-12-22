package com.example.nadercodechallenge1.ui.moresettings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.nadercodechallenge1.R
import com.example.nadercodechallenge1.databinding.MoreSettingsFragmentBinding
import com.example.nadercodechallenge1.ui.base.ScopedFragment
import com.example.nadercodechallenge1.ui.more.MoreSettingsViewModel
import com.example.nadercodechallenge1.ui.settings.SettingsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoreSettingsFragment : ScopedFragment() {


    private var _binding: MoreSettingsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    private val shareViewModel: SettingsViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    private val viewModel: MoreSettingsViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MoreSettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        bindUI()
    }


    private fun bindUI()= launch(Dispatchers.Main){
        val currentArticle = shareViewModel.article.await()

        currentArticle.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            Log.e("more settings fragment",it.toString())

        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}