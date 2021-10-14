package com.ll.api

import com.ll.bean.WanAndroidLoginInfoBean
import com.ll.bean.WanAndroidLoginInfoWrapper
import com.ll.bean.WanAndroidLoginInfoUserInfoBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *  玩安卓api测试机
 */
interface WanAndroidApi {
    @POST("/user/login")
    @FormUrlEncoded
    fun loginAction(@Field("username") userName: String,
                    @Field("password") password: String)
            : Observable<WanAndroidLoginInfoWrapper<WanAndroidLoginInfoUserInfoBean>>

    @POST("/user/register")
    @FormUrlEncoded
    fun registerAction(@Field("username") username: String,
                       @Field("password") password: String,
                       @Field("repassword") repassword: String)
            : Observable<WanAndroidLoginInfoWrapper<WanAndroidLoginInfoUserInfoBean>>

    @POST("/user/lg/userinfo/json")
    @FormUrlEncoded
    fun getUserInfoAction(@Field("username") username: String,
                       @Field("password") password: String)
            : Observable<WanAndroidLoginInfoWrapper<WanAndroidLoginInfoBean>>
}