package com.basemibrahim.redditnews.utils

import androidx.room.TypeConverter
import com.basemibrahim.redditnews.data.model.api.Children
import com.basemibrahim.redditnews.data.model.api.Data
import com.basemibrahim.redditnews.data.model.api.DataX
import com.basemibrahim.redditnews.data.model.api.NewsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromNewsResponse(value: NewsResponse): String {
        val gson = Gson()
        val type = object : TypeToken<NewsResponse>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toNewsResponse(value: String): NewsResponse {
        val gson = Gson()
        val type = object : TypeToken<NewsResponse>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromData(value: Data): String {
        val gson = Gson()
        val type = object : TypeToken<Data>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toData(value: String): Data {
        val gson = Gson()
        val type = object : TypeToken<Data>() {}.type
        return gson.fromJson(value, type)
    }
}