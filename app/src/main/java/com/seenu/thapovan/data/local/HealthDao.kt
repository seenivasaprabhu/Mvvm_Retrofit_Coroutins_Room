package com.seenu.thapovan.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seenu.thapovan.data.entities.HealthModel
import com.seenu.thapovan.utils.DB_TableName
//DAO class for Room DB
@Dao
    interface HealthDao {
	// Query to get all health record from room database
        @Query("SELECT * FROM $DB_TableName")
        fun getAllHealthRecords() : LiveData<HealthModel>
	//Insert all records to room database
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertAll(healthRecords: HealthModel)

    }
