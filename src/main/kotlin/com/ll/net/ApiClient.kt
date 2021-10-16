package com.ll.net

import com.ll.interceptor.OkHttpInterceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
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
    fun <T> createApiXmlConvert(apiInterface: Class<T>, baseUrl : String): T {
        return createApi(apiInterface, baseUrl, SimpleXmlConverterFactory.create(), RxJava2CallAdapterFactory.create())
    }

    fun <T> createApiStringConvert(apiInterface: Class<T>, baseUrl : String): T {
        return createApi(apiInterface, baseUrl, ScalarsConverterFactory.create(), RxJava2CallAdapterFactory.create())
    }

    fun <T> createApiGsonConvert(apiInterface: Class<T>, baseUrl : String): T {
        return createApi(apiInterface, baseUrl, GsonConverterFactory.create(), RxJava2CallAdapterFactory.create())
    }

    private fun <T> createApi(apiInterface: Class<T>, baseUrl : String,
                              converter: Converter.Factory,
                              adapter: CallAdapter.Factory): T {
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
                .addConverterFactory(converter)
                .addCallAdapterFactory(adapter)
                .build()
        return mRetrofit.create(apiInterface)
    }
}