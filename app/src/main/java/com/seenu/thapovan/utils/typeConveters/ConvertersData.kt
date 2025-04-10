package com.seenu.thapovan.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.seenu.thapovan.data.entities.HealthModel

//Type converter class for access data model
  class ConvertersData{
	// convert model to string
    @TypeConverter
    fun toHealthJson(data:HealthModel.Data):String{
        return Gson().toJson(data)
    }
	//convert string to model
    @TypeConverter
    fun fromHealthJson(json:String):HealthModel.Data{
        return Gson().fromJson<HealthModel.Data>(json)
    }
    private inline fun <reified T> Gson.fromJson(json:String) = fromJson<T>(json,object :TypeToken<T>(){}.type)

}