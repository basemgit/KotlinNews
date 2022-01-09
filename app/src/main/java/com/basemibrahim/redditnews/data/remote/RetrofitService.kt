package com.basemibrahim.redditnews.data.remote

import com.basemibrahim.redditnews.data.model.api.NewsResponse
import com.basemibrahim.redditnews.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET(Constants.NEWS_URL)
    suspend fun getNews(): Response<NewsResponse>
}