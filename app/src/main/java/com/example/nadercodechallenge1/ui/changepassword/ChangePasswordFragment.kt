package com.example.nadercodechallenge1.ui.changepassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.nadercodechallenge1.databinding.FragmentChangePasswordBinding
import com.example.nadercodechallenge1.internal.Utils.ValidationManager
import com.example.nadercodechallenge1.ui.addDataToPrefs
import com.example.nadercodechallenge1.ui.base.DefaultFragment
import com.example.nadercodechallenge1.ui.getCurrentAccount
import com.example.nadercodechallenge1.ui.getTextTrimed
import es.dmoral.toasty.Toasty

class ChangePasswordFragment : DefaultFragment() {
    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.changePasswordBtn.setOnClickListener {
            val oldPassword = binding.oldPasswordField.getTextTrimed()
            val newPassword = binding.newPasswordField.getTextTrimed()
            val confPassword = binding.confPasswordField.getTextTrimed()


            var passwordfield = false
            var matchpasswordfield = false
            var oldPassConf = false

            if(ValidationManager.isValidPassword(newPassword)){
                passwordfield =true
            }else{
                binding.newPasswordField.error = "Please enter a valid password"
            }
            if(ValidationManager.isPasswordMatch(newPassword,confPassword)){
                matchpasswordfield =true
            }else{
                binding.confPasswordField.error = "Passwords does not match"
            }
            val account = activity?.getCurrentAccount()
            if(account!!.password!!.equals(oldPassword)){
                oldPassConf =true
            }else{
                binding.oldPasswordField.error = "Enter the old Password"
            }


            if(passwordfield && matchpasswordfield && oldPassConf){
                val newAccount:HashMap<String,String> = HashMap()
                newAccount["firstname"] = account.firstName!!
                newAccount["lastname"] = account.lastName!!
                newAccount["email"] = account.email!!
                newAccount["password"] = newPassword

                activity?.let {
                    it.addDataToPrefs(newAccount)
                    Toasty.success(it, "Success! account updated successfully!", Toast.LENGTH_SHORT, true).show()
                    navController.popBackStack()
                }

            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}