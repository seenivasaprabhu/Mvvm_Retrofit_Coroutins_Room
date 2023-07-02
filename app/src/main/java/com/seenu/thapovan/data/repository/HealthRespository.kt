package com.seenu.thapovan.data.repository

import com.seenu.thapovan.data.local.HealthDao
import com.seenu.thapovan.data.remote.HealthRemoteDataSource
import com.seenu.thapovan.utils.DataAccess
import javax.inject.Inject

//Repositary class to get server response and add room database 
class HealthRepository @Inject constructor(
    private val remoteDataSource: HealthRemoteDataSource,
    private val localDataSource: HealthDao
) {
//Function for perform data access operation from server to db
    fun getHealthRecords() = DataAccess.performOperation(
	//Fetch records from room db
        databaseQuery = { localDataSource.getAllHealthRecords() },
	//Get records from server
        networkCall = { remoteDataSource.getHealthRecords() },
	//Save records to room db 
        saveCallResult = { model -> localDataSource.insertAll(model) }
    )
}