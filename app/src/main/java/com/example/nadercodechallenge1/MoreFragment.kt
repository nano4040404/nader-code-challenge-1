package com.example.nadercodechallenge1

import android.Manifest
import android.R.attr
import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.nadercodechallenge1.databinding.FragmentMoreBinding


class MoreFragment : Fragment() {
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val image = activity?.getImage()
        if (image != null){
            binding.profileImageView.setImageBitmap(image)
        }



        binding.editProfileBtn.setOnClickListener{
            navController.navigate(R.id.action_moreFragment_to_editProfileFragment)

        }
        binding.changePasswordBtn.setOnClickListener{
            navController.navigate(R.id.action_moreFragment_to_changePasswordFragment)
        }
        binding.aboutUsBtn.setOnClickListener{
            navController.navigate(R.id.action_moreFragment_to_aboutUsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


