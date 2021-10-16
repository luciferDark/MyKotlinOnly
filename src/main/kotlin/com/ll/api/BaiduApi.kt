package com.ll.api

import io.reactivex.Observable
import retrofit2.http.GET

interface BaiduApi {

    @GET("/")
    fun getWebSrc() : Observable<String>
}