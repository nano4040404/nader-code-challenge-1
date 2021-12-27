package com.example.nadercodechallenge1.presentation.ui.settings


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.nadercodechallenge1.R
import com.example.nadercodechallenge1.databinding.SettingsFragmentBinding
import com.example.nadercodechallenge1.presentation.ui.base.ScopedFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SettingsFragment : ScopedFragment() {

    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!


    private lateinit var navController: NavController


    private val viewModel: SettingsViewModel by hiltNavGraphViewModels(R.id.nav_graph)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        bindUI()

        binding.button.setOnClickListener {
            navController.navigate(R.id.action_settingsFragment_to_moreSettingsFragment)
        }

    }

    private fun bindUI()= launch(Dispatchers.Main){
        val currentArticle = viewModel.article.await()

        currentArticle.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            Log.e("settings fragment",it.toString())

        })
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}