package com.plcoding.mycashtask.yjahz.data.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    val addresses: List<Address>? = null,
    val balance: Double? = null,
    val email: String,
    @PrimaryKey val id: Int = 1,
    val image: String? = null,
    val name: String? = null,
    val phone: String? = null,
    val status: Int? = null,
    val token: String? = null,
    val type: Int? = null
)

data class Address(
    val id: Int,
    val lat: String,
    val lng: String,
    val address: String?,
    val street: String?,
    val building: String?,
    val apartment: String?,
    val floor: String?,
    val name: String
)
