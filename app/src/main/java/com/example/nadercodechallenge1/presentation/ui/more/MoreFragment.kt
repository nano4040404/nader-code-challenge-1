package com.example.nadercodechallenge1.presentation.ui.more

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.nadercodechallenge1.R
import com.example.nadercodechallenge1.databinding.FragmentMoreBinding
import com.example.nadercodechallenge1.presentation.ui.base.DefaultFragment
import com.example.nadercodechallenge1.presentation.ui.getImage
import com.example.nadercodechallenge1.presentation.ui.saveTheme
import java.util.concurrent.Executor


class MoreFragment : DefaultFragment() {
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    private val viewModel: MoreSettingsViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

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
        activity?.let {
            executor = ContextCompat.getMainExecutor(it)
            initSecurity(it)
        }


        val image = activity?.getImage()
        if (image != null){
            binding.profileImageView.setImageBitmap(image)
        }



//        binding.editProfileBtn.setOnClickListener{
//            navController.navigate(R.id.action_moreFragment_to_editProfileFragment)
//
//        }
        binding.changePasswordBtn.setOnClickListener{
            navController.navigate(R.id.action_moreFragment_to_changePasswordFragment)
        }
        binding.aboutUsBtn.setOnClickListener{
            navController.navigate(R.id.action_moreFragment_to_aboutUsFragment)
        }
        binding.changeThemeBtn.setOnClickListener {
            chooseThemeDialog()
        }
    }

    private fun chooseThemeDialog(){
        activity?.let { it ->
            val builder = AlertDialog.Builder(it)
            builder.setTitle(getString(R.string.choose_theme_text))
            val styles = arrayOf("Light","Dark","System default")
            val checkedItem = 0

            builder.setSingleChoiceItems(styles, checkedItem) { dialog, which ->
                viewModel.changeTheme(which)
                it.saveTheme(which)
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }

    }

    private fun initSecurity(context: Context){
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(context,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(context,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                    navController.navigate(R.id.action_moreFragment_to_editProfileFragment)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(context, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
        binding.editProfileBtn.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


