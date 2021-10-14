package com.ll.bean

/**
 *
    "data": {
    },
    "errorCode": 0,
    "errorMsg": ""
 */
data class WanAndroidLoginInfoWrapper<T>(
        val data: T?,
        val errorCode: Int,
        val errorMsg: String)