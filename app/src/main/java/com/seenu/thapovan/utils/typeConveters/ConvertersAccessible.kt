package com.seenu.thapovan.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.seenu.thapovan.data.entities.HealthModel

//Type converter class for access health data
class ConvertersAccessible {
	//function for convert model to string
    @TypeConverter
    fun toHealthJson(data:ArrayList<HealthModel.Data.Health>):String{
        return Gson().toJson(data)
    }
	//function for convert string to model
    @TypeConverter
    fun fromHealthJson(json:String):ArrayList<HealthModel.Data.Health>{
        return Gson().fromJson<ArrayList<HealthModel.Data.Health>>(json)
    }
	//inline function for gson with generic type
    private inline fun <reified T> Gson.fromJson(json:String) = fromJson<T>(json,object :TypeToken<T>(){}.type)

}