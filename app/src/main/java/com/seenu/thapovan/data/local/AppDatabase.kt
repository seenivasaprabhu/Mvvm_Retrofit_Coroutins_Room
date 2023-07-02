package com.seenu.thapovan.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.seenu.thapovan.data.entities.HealthModel
import com.seenu.thapovan.utils.ConvertersAccessible
import com.seenu.thapovan.utils.ConvertersData
import com.seenu.thapovan.utils.ConvertersModel
import com.seenu.thapovan.utils.DB_Name

//Database class for Room DB
@Database(entities = [HealthModel::class], version = 1, exportSchema = false)
@TypeConverters(value = [ConvertersModel::class, ConvertersData::class, ConvertersAccessible::class])
abstract class AppDatabase : RoomDatabase() {
	//Abstract method for access DAO 
    abstract fun healthDao(): HealthDao

	//Singleton design pattern to access database instanse
    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, DB_Name)
                .fallbackToDestructiveMigration()
                .build()
    }

}