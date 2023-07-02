package com.seenu.thapovan.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.seenu.thapovan.data.local.AppDatabase
import com.seenu.thapovan.data.local.HealthDao
import com.seenu.thapovan.data.remote.HealthApiService
import com.seenu.thapovan.data.remote.HealthRemoteDataSource
import com.seenu.thapovan.data.repository.HealthRepository
import com.seenu.thapovan.utils.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Hilt Module class for access services 
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
	// fun to privide retrofit instanse for app
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
	// fun to privide gson instanse for retrofit
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()
	// fun to privide api service
    @Provides
    fun provideHealthApiService(retrofit: Retrofit): HealthApiService = retrofit.create(HealthApiService::class.java)

	// fun to privide remotedatasource
    @Singleton
    @Provides
    fun provideHealthRemoteDataSource(apiService: HealthApiService) = HealthRemoteDataSource(apiService)
	// fun to privide database
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)
	// fun to privide DAO 
    @Singleton
    @Provides
    fun provideHealthDao(db: AppDatabase) = db.healthDao()
	// fun to privide repository
    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: HealthRemoteDataSource,
                          localDataSource: HealthDao) =
        HealthRepository(remoteDataSource, localDataSource)
}