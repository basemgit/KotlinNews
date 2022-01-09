package com.basemibrahim.redditnews.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.basemibrahim.redditnews.data.Repository
import com.basemibrahim.redditnews.data.model.api.NewsResponse
import com.basemibrahim.redditnews.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<NewsResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<NewsResponse>>  = _response
    private val _responseDB: MutableLiveData<NewsResponse> = MutableLiveData()
    val responseDB: LiveData<NewsResponse>  = _responseDB

    fun fetchNewsResponse() = viewModelScope.launch {
        repository.getNews().collect { values ->
            _response.value = values
        }
    }

    fun fetchResponseFromDb() = viewModelScope.launch {
        repository.getResponseFromDb().collect { values ->
            _responseDB.value = values
        }
    }

    fun saveResponseToDb(response: NewsResponse) = viewModelScope.launch {
        repository.saveResponse(response)
    }


}