package com.siele.mkononiapptest.api

import com.siele.mkononiapptest.model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiClient {
    @POST("auth/register")
    suspend fun registerUser(@Body user: User): Response<User>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun loginUser(
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Response<Any>
}
    private val BASE_URL = "https://api-stage.mkononi.biz/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    object AuthApi{
        val apiClient:ApiClient by lazy {
            retrofit.create(ApiClient::class.java)
        }
    }
