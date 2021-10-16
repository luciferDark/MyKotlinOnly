package com.ll

import com.ll.api.BaiduApi
import com.ll.net.ApiClient
import com.ll.api.WanAndroidApi
import com.ll.bean.WanAndroidLoginInfoBean
import com.ll.bean.WanAndroidLoginInfoUserInfoBean
import com.ll.interceptor.WanAndroidLoginInterceptor
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

val baseUrl = "https://wanandroid.com"
var userName = "kylin02"
var password = "123456"

fun main(args: Array<String>) {
    println("createApi userName is $userName, and password is $password")
    val wanAndroidApi : WanAndroidApi =  ApiClient.instance.createApiGsonConvert(WanAndroidApi::class.java, baseUrl)
//    register(wanAndroidApi)
    login(wanAndroidApi)
    getuserInfo(wanAndroidApi)

    getBaiduWebSrc()
}

fun getBaiduWebSrc() {
    val baiduUrl = "https://www.baidu.com"
    val baiduApi :BaiduApi = ApiClient.instance.createApiStringConvert(BaiduApi::class.java,baiduUrl)
    baiduApi.getWebSrc()
            .subscribe {
//                println("get baidu web src is : $it")
                val doc: Document = Jsoup.parse(it)
//                doc.getElementsByTag("a").forEach {
//                    println(it.attr("href")  + "<==>"+ it.ownText())
//                }
                var srcs = doc.select("a[href]")
                srcs.forEach {
                    println(it.attr("href")  + "<==>"+ it.ownText())
                }
                srcs = doc.select("img[src]")
                if (srcs.size >0)
                    srcs.forEach { println(it.attr("src")  + "<==>"+ it.ownText()) }
                else
                    println("get no img")
            }
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