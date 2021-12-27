package com.example.nadercodechallenge1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nadercodechallenge1.data.db.current.CurrentArticleEntry
import com.example.nadercodechallenge1.data.db.entity.Result

@Dao
interface CurrentArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(result: List<Result>)

    @Query("select * from article_info")
    fun getArticles(): LiveData<List<CurrentArticleEntry>>

    @Query("delete from article_info")
    fun deleteAllArticles()
}