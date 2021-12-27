package com.example.nadercodechallenge1.data.db.current

import com.example.nadercodechallenge1.utils.AppConstants.EMPTY_INT
import com.example.nadercodechallenge1.utils.AppConstants.EMPTY_STRING

data class CurrentArticleEntry(
    var abstract: String,
    var byline: String?,
    var nytdsection: String?,
    var publishedDate: String?,
    var section: String?,
    var source: String?,
    var subsection: String?,
    var title: String?,
    var type: String?,
    var updated: String?,
    var uri: String?,
    var url: String?,
    var id: Long?

){
    constructor() : this(EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,
        EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,EMPTY_INT)
}