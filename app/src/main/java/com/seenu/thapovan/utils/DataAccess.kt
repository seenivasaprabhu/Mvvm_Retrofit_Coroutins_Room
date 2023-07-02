package com.seenu.thapovan.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

//object for data access statergy from server -> db and db -> viewmodel with help of corourines dispatcher.IO and live data
object DataAccess{
    fun <T, A> performOperation(databaseQuery: () -> LiveData<T>,
                                   networkCall: suspend () -> Resource<A>,
                                   saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
		// fetch data frrom database
            val source = databaseQuery.invoke().map { Resource.success(it) }
            emitSource(source)
		// server call 
            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Resource.Status.SUCCESS) {
                //bindind server response to data base
                saveCallResult(responseStatus.data!!)

            } else if (responseStatus.status == Resource.Status.ERROR) {
		//emit error reponse
                emit(Resource.error(responseStatus.message!!))
                emitSource(source)
            }
        }
}
