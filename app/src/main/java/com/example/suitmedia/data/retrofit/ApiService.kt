package com.example.suitmedia.data.retrofit

import com.example.suitmedia.data.response.ReqresResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend  fun getListUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        ): ReqresResponse
}