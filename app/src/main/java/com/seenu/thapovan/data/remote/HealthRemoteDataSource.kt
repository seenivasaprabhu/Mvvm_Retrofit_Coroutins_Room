package com.seenu.thapovan.data.remote

import javax.inject.Inject

// Remotedatasource class for getting response
class HealthRemoteDataSource @Inject constructor(
    private val apiService: HealthApiService
): BaseDataSource() {
// Suspend function for getting response
    suspend fun getHealthRecords() = getResult { apiService.getAllHealthRecords() }
}