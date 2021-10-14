package com.ll.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 *  OkHttp拦截器
 */
class OkHttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original : Request= chain.request()
        val build : Request.Builder = original.newBuilder()
                .method(original.method(), original.body())
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
        val  finalRequest : Request = build.build()
        return  chain.proceed(finalRequest)
    }
}