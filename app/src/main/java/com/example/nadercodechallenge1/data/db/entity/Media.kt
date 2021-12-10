package com.example.nadercodechallenge1.data.db.entity


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

data class Media(
    @SerializedName("approved_for_syndication")
    val approvedForSyndication: Int,
    val caption: String,
    val copyright: String,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata>,
    val subtype: String,
    val type: String
)

class MediaMetaDataTypeConverter{
    @TypeConverter
    fun fromString(value: String?): List<MediaMetadata>{
        val listType = object: TypeToken<List<MediaMetadata>>(){}.type
        return Gson().fromJson(value,listType )
    }
    @TypeConverter
    fun frmList(list: List<MediaMetadata>): String{

        return Gson().toJson(list)
    }


}
