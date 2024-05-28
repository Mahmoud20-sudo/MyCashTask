package com.plcoding.mycashtask.yjahz.data.api

import com.plcoding.mycashtask.yjahz.data.model.catrgories.CategoryModel
import com.plcoding.mycashtask.yjahz.data.model.sellers.PopularSeller
import com.plcoding.mycashtask.yjahz.data.model.user.UserModel
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

interface Api {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_token") deviceToken: String = "12233454566787877",
    ): Response<UserModel>

    @FormUrlEncoded
    @POST("client-register")
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("phone") phone: String
    ): Response<UserModel>

    @GET("popular-sellers?")
    suspend fun popularNow(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("filter") filter: Int = 1
    ): Response<PopularSeller>

    @GET("trending-sellers?")
    suspend fun trending(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("filter") filter: Int = 1
    ): Response<PopularSeller>

    @GET("base-categories")
    suspend fun categories(): Response<CategoryModel>
}