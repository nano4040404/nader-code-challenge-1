package com.example.nadercodechallenge1.data.db.current

import com.example.nadercodechallenge1.data.db.current.CurrentArticleEntry.DbConstants.EMPTY_INT
import com.example.nadercodechallenge1.data.db.current.CurrentArticleEntry.DbConstants.EMPTY_STRING

data class CurrentArticleEntry(
    var abstract: String?,
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
    object DbConstants {
        const val EMPTY_STRING = ""
        const val EMPTY_INT: Long = 0
    }
    constructor() : this(EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,
        EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,EMPTY_STRING,EMPTY_INT)
}