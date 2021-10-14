package com.ll.net

import com.ll.interceptor.OkHttpInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    private object Holder {
        val INSTANCE = ApiClient()
    }

    companion object {
        val instance = Holder.INSTANCE
    }

    /**
     *  创建api接口
     */
    fun <T> createApi(apiInterface: Class<T>, baseUrl : String): T {
        val mOkHttpClient = OkHttpClient.Builder()
                .callTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(OkHttpInterceptor())
                .build()
        println("baseUrl is $baseUrl")
        val mRetrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return mRetrofit.create(apiInterface)
    }
}