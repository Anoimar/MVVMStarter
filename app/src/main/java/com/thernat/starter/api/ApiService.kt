package com.thernat.starter.api

import com.thernat.starter.vo.BasicElement
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by m.rafalski on 07/06/2019.
 */
interface ApiService {

    @GET(".")
    suspend fun getBasicElementAsync(): Response<BasicElement>
}