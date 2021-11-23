package com.example.nadercodechallenge1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.nadercodechallenge1.databinding.FragmentMainBinding
import es.dmoral.toasty.Toasty

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val account = activity?.getCurrentAccount()

        binding.fullNameField.text = "Welcome ${account?.firstName} ${account?.LastName}"

        binding.newsBtn.setOnClickListener{
            navController.navigate(R.id.action_mainFragment_to_newsFragment)
        }
        binding.moretBtn.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_moreFragment)
        }

        binding.logoutBtn.setOnClickListener{
            activity?.let{
                it.deleteAccount()
                Toasty.warning(it, "Account removed", Toast.LENGTH_SHORT, true).show()
                val intent = Intent (it, LoginActivity::class.java)
                it.startActivity(intent)
                it.finish()
            }

        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}