package com.basemibrahim.redditnews.data.local.dp.dao;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.basemibrahim.redditnews.data.model.api.NewsResponse


@Dao
interface ChildrenDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertResponse(item: NewsResponse)


    @Query("select * from newsresponse")
    fun getResponse() : NewsResponse



}
