package com.seenu.thapovan.utils

import com.google.gson.Gson
import java.lang.reflect.Type

// Generic gson parser class
class GsonParser(private val gson:Gson) :JsonParser{
    override fun <T> fromJson(json: String, type: Type): T? {
        return gson.fromJson(json,type)
    }

    override fun <T> toJson(obj: T, type: Type): String? {
        return gson.toJson(obj,type)
    }
}