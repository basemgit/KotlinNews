

package com.basemibrahim.redditnews.data.local

import com.basemibrahim.redditnews.data.local.dp.dao.ChildrenDao
import com.basemibrahim.redditnews.data.model.api.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalDataSource @Inject constructor(private val childrenDao: ChildrenDao) {

    suspend  fun saveResponse (response: NewsResponse) {
       withContext(Dispatchers.IO)
       {
           childrenDao.insertResponse(response)
       }
    }

    suspend fun getResponse() : NewsResponse {
       var response : NewsResponse
       withContext(Dispatchers.IO)
       {
           response = childrenDao.getResponse()
       }
       return response
    }


}
