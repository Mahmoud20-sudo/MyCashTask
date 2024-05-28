package com.plcoding.mycashtask.yjahz.data.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.plcoding.mycashtask.yjahz.data.model.user.Address
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.dom.Comment

class Converters {
//    @TypeConverter
//    fun convertListToJsonString(items: List<String>): String =
//        Json.encodeToString(items)
//
//    @TypeConverter
//    fun convertJsonStringToList(json: String): List<String> =
//        Json.decodeFromString(json)

    @TypeConverter
    fun fromGroupTaskMemberList(value: List<Address>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Address>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskMemberList(value: String): List<Address> {
        val gson = Gson()
        val type = object : TypeToken<List<Address>>() {}.type
        return gson.fromJson(value, type)
    }
}