package com.seenu.thapovan.data.remote

import com.seenu.thapovan.data.entities.HealthModel
import com.seenu.thapovan.utils.EndPoint
import retrofit2.Response
import retrofit2.http.GET

//Interface for API service
interface HealthApiService {
    @GET(EndPoint)
	//Suspend function to get response from server
    suspend fun getAllHealthRecords() : Response<HealthModel>

}