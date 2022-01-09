package com.basemibrahim.redditnews.data

import com.basemibrahim.redditnews.data.local.LocalDataSource
import com.basemibrahim.redditnews.data.model.api.BaseApiResponse
import com.basemibrahim.redditnews.data.model.api.NewsResponse
import com.basemibrahim.redditnews.data.remote.RemoteDataSource
import com.basemibrahim.redditnews.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : BaseApiResponse() {

    suspend fun getNews(): Flow<NetworkResult<NewsResponse>> {
        return flow<NetworkResult<NewsResponse>> {
            emit(safeApiCall { remoteDataSource.getNews() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getResponseFromDb(): Flow<NewsResponse> {
        return flow<NewsResponse> {
            emit(localDataSource.getResponse())
        }.flowOn(Dispatchers.IO)
    }
    suspend fun saveResponse(response: NewsResponse) {
        localDataSource.saveResponse(response)
    }



}