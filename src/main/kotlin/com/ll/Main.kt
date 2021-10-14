package com.ll

import com.ll.net.ApiClient
import com.ll.api.WanAndroidApi
import com.ll.bean.WanAndroidLoginInfoBean
import com.ll.bean.WanAndroidLoginInfoUserInfoBean
import com.ll.interceptor.WanAndroidLoginInterceptor

val baseUrl = "https://wanandroid.com"
var userName = "kylin02"
var password = "123456"

fun main(args: Array<String>) {
    println("createApi userName is $userName, and password is $password")
    val wanAndroidApi : WanAndroidApi =  ApiClient.instance.createApi(WanAndroidApi::class.java, baseUrl)
//    register(wanAndroidApi)
    login(wanAndroidApi)
    getuserInfo(wanAndroidApi)
}

fun login(wanAndroidApi:WanAndroidApi){
    wanAndroidApi
            .loginAction(userName, password)
            .subscribe(object : WanAndroidLoginInterceptor<WanAndroidLoginInfoUserInfoBean>(true) {
                override fun onFailure(errorCode: Int, errorMsg: String) {
                    println("Failure errorCode is $errorCode, and errorMsg is $errorMsg")
                }
                override fun onSuccess(data: WanAndroidLoginInfoUserInfoBean) {
                    println("Success data is $data")
                }
            })
}

fun getuserInfo(wanAndroidApi:WanAndroidApi){
    wanAndroidApi
            .getUserInfoAction(userName, password)
            .subscribe(object : WanAndroidLoginInterceptor<WanAndroidLoginInfoBean>(true) {
                override fun onFailure(errorCode: Int, errorMsg: String) {
                    println("Failure errorCode is $errorCode, and errorMsg is $errorMsg")
                }
                override fun onSuccess(data: WanAndroidLoginInfoBean) {
                    println("Success data is $data")
                }
            })
}

fun register(wanAndroidApi:WanAndroidApi){
    wanAndroidApi
            .registerAction(userName, password, password)
            .subscribe(object : WanAndroidLoginInterceptor<WanAndroidLoginInfoUserInfoBean>(true) {
                override fun onFailure(errorCode: Int, errorMsg: String) {
                    println("Failure errorCode is $errorCode, and errorMsg is $errorMsg")
                }
                override fun onSuccess(data: WanAndroidLoginInfoUserInfoBean) {
                    println("Success data is $data")
                }
            })
}