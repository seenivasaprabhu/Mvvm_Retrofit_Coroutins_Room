package com.seenu.thapovan.data.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.seenu.thapovan.utils.ConvertersAccessible
import com.seenu.thapovan.utils.ConvertersData
import com.seenu.thapovan.utils.ConvertersHealth
import com.seenu.thapovan.utils.DB_TableName


@Entity(tableName = DB_TableName)
data class HealthModel(
    @ColumnInfo(name = "statusCode") val statusCode: Int = 0,
    @ColumnInfo(name = "data") val data: Data =  Data(),
    @ColumnInfo(name = "success") val success: Boolean = false,
    @PrimaryKey(autoGenerate = true) var id: Int ? = 0
){

    data class Data(
        @ColumnInfo(name = "health") val health: List<Health> = listOf(),
        @ColumnInfo(name = "message") val message: String = "",
        @PrimaryKey(autoGenerate = true) var id: Int ? = 0
    ) {

        data class Health(
            @ColumnInfo(name = "accessible") val accessible: List<Accessible> = listOf(),
            @ColumnInfo(name = "name") val name: String = "",
            @PrimaryKey(autoGenerate = true) var id: Int ? = 0
        ) {
            @Entity
            data class Accessible(
                @PrimaryKey(autoGenerate = true) var id: Int ? = 0,
                @ColumnInfo(name = "api") val api: String? = null,
                @ColumnInfo(name = "message") val message: String = "",
                @ColumnInfo(name = "name") val name: String? = null,
                @ColumnInfo(name = "status") val status: Int? = null,
                @ColumnInfo(name = "success") val success: Boolean = false,
                @ColumnInfo(name = "type") val type: String? = null
            )
        }
    }

}
