package com.example.nadercodechallenge1.presentation.ui.editprofile

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.nadercodechallenge1.R
import com.example.nadercodechallenge1.databinding.FragmentEditProfileBinding
import com.example.nadercodechallenge1.presentation.ui.*
import com.example.nadercodechallenge1.presentation.ui.base.DefaultFragment
import com.example.nadercodechallenge1.utils.ValidationManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import es.dmoral.toasty.Toasty
import java.util.*
import kotlin.collections.HashMap


class EditProfileFragment : DefaultFragment() {
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController


    private val viewModel: EditProfileViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private var readPermissionGranted = false
    private var writePermissionGranted = false
    private lateinit var permissionsLauncher: ActivityResultLauncher<Array<String>>

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->

        if (uri !=null){
            activity?.let { it.saveImage(uri.toBitmap(it).toBase64()) }
            binding.profileImageView.setImageURI(uri)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        permissionsLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            readPermissionGranted = permissions[Manifest.permission.READ_EXTERNAL_STORAGE] ?: readPermissionGranted
            writePermissionGranted = permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] ?: writePermissionGranted


        }
        updateOrRequestPermissions()





        val account = activity?.getCurrentAccount()
        binding.emailField.text = account!!.email
        val image = activity?.getImage()
        if (image != null){
            binding.profileImageView.setImageBitmap(image)
        }
        binding.profileImageView.setOnClickListener{
            showBottomSheetDialog()
        }

        binding.submit.setOnClickListener{
            val firstName = binding.FirstNameField.getTextTrimed()
            val lastName = binding.lastNameField.getTextTrimed()
            var firstNameField = false
            var lastNameField = false

            if(ValidationManager.isFieldValid(firstName)){
                firstNameField =true
            }else{
                binding.FirstNameField.error = "Please enter a valid name"
            }

            if(ValidationManager.isFieldValid(lastName)){
                lastNameField =true
            }else{
                binding.lastNameField.error = "Please enter a valid name"
            }
            if(firstNameField && lastNameField ){
                val newAccount:HashMap<String,String> = HashMap()
                newAccount["firstname"] = firstName
                newAccount["lastname"] = lastName
                newAccount["email"] = account.email!!
                newAccount["password"] = account.password!!

                activity?.let {
                    it.addDataToPrefs(newAccount)
                    Toasty.success(it, "Success! account updated successfully!", Toast.LENGTH_SHORT, true).show()
                    navController.popBackStack()

                }

            }
        }


    }

    fun showBottomSheetDialog(){
        activity?.let {
            val bottomSheetDialog = BottomSheetDialog(it)
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout)

            val camera = bottomSheetDialog.findViewById<LinearLayout>(R.id.camera)
            val galery = bottomSheetDialog.findViewById<LinearLayout>(R.id.galery)

            bottomSheetDialog.show()

            camera?.setOnClickListener{


                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try {
                    resultLauncher.launch(takePictureIntent)
                } catch (e: ActivityNotFoundException) {
                    Toasty.error(requireActivity(), "Success! account updated successfully!", Toast.LENGTH_SHORT, true).show()
                }
                bottomSheetDialog.dismiss()
            }
            galery?.setOnClickListener{
                getContent.launch("image/*")
                bottomSheetDialog.dismiss()
            }

        }

    }
    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                val imageBitmap = data?.extras?.get("data") as Bitmap
                activity?.let {
                    it.saveImage(imageBitmap.toBase64())

                    val isSavedSuccessfully = viewModel.savePhotoToExternalStorage(UUID.randomUUID().toString(),imageBitmap)
                    val isSavedSuccessfullyInternal = viewModel.savePhotoToInternalStorage(UUID.randomUUID().toString(),imageBitmap)

                    if(isSavedSuccessfully && isSavedSuccessfullyInternal) {
                        Toast.makeText(it, "Photo saved successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(it, "Failed to save photo", Toast.LENGTH_SHORT).show()
                    }

                }
                binding.profileImageView.setImageBitmap(imageBitmap)

            }
        }


    private fun updateOrRequestPermissions() {
        activity?.let {
            val hasReadPermission = ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
            val hasWritePermission = ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
            val minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

            readPermissionGranted = hasReadPermission
            writePermissionGranted = hasWritePermission || minSdk29

            val permissionsToRequest = mutableListOf<String>()
            if(!writePermissionGranted) {
                permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            if(!readPermissionGranted) {
                permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            if(permissionsToRequest.isNotEmpty()) {
                permissionsLauncher.launch(permissionsToRequest.toTypedArray())
            }
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}