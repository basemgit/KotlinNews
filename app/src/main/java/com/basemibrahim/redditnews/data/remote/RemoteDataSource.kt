package com.basemibrahim.redditnews.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val retrofitService: RetrofitService) {

    suspend fun getNews() =
        retrofitService.getNews()

}