package com.example.nadercodechallenge1.ui.aboutus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.nadercodechallenge1.databinding.FragmentAboutUsBinding
import com.example.nadercodechallenge1.ui.base.DefaultFragment


class AboutUsFragment : DefaultFragment() {
    private var _binding: FragmentAboutUsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        val url ="https://euriskomobility.com/what-we-do/"
        binding.aboutUsWebView.settings.javaScriptEnabled

        binding.aboutUsWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url.toString())
                return true
            }
        }
        binding.aboutUsWebView.loadUrl(url)

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}