package com.example.nadercodechallenge1.presentation.ui.editprofile

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import com.example.nadercodechallenge1.utils.sdk29AndUp
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val context: Context
):ViewModel() {
     fun savePhotoToExternalStorage(displayName: String, bmp: Bitmap): Boolean {
            val imageCollection = sdk29AndUp {
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            } ?: MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "$displayName.jpg")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.WIDTH, bmp.width)
                put(MediaStore.Images.Media.HEIGHT, bmp.height)
            }
            return try {
                context.contentResolver.insert(imageCollection, contentValues)?.also { uri ->
                    context.contentResolver.openOutputStream(uri).use { outputStream ->
                        if(!bmp.compress(Bitmap.CompressFormat.JPEG, 95, outputStream)) {
                            throw IOException("Couldn't save bitmap")
                        }
                    }
                } ?: throw IOException("Couldn't create MediaStore entry")
                true
            } catch(e: IOException) {
                e.printStackTrace()
                false
            }
    }

     fun savePhotoToInternalStorage(filename: String, bmp: Bitmap): Boolean {
        return try {
            context.openFileOutput("$filename.jpg", MODE_PRIVATE).use { stream ->
                if(!bmp.compress(Bitmap.CompressFormat.JPEG, 95, stream)) {
                    throw IOException("Couldn't save bitmap.")
                }
            }
            true
        } catch(e: IOException) {
            e.printStackTrace()
            false
        }
    }


//    suspend fun loadPhotosFromInternalStorage(): List<InternalStoragePhoto> {
//        return withContext(Dispatchers.IO) {
//            val files = context.filesDir.listFiles()
//            files?.filter { it.canRead() && it.isFile && it.endsWith(".jpg") }?.map {
//                val bytes = it.readBytes()
//                val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
//                InternalStoragePhoto(it.name, bmp)
//            } ?: listOf()
//        }
//    }


}