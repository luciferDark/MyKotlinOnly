package com.ll.bean

/**
 * LoginInfo
 "data": {
        "coinInfo": {
        },
        "userInfo": {
        }
},
 */
data class WanAndroidLoginInfoBean(
        val coinInfo : WanAndroidLoginInfoCoinInfoBean,
        val userInfo : WanAndroidLoginInfoUserInfoBean
)

/**
    "coinInfo": { // 积分和排名可能不是实时的，每天更新
        "coinCount": 36662, // 可用
        "level": 367, // 可用
        "nickname": "",
        "rank": "3", // 可用
        "userId": 2, // 可用
        "username": "x**oyang"
    },
 */
data class WanAndroidLoginInfoCoinInfoBean(
        val  coinCount: Int,
        val  level: Int,
        val  nickname: String?,
        val  rank: String?,
        val  userId: Int,
        val  username: String?
)

/**
    "userInfo": {
        "admin": false,
        "chapterTops": [],
        "coinCount": 36662, // 可用
        "collectIds": [ ]// 可用
        "email": "623565791@qq.com", // 可用
        "icon": "",
        "id": 2, // 可用
        "nickname": "鸿洋",// 可用
        "password": "",
        "publicName": "鸿洋",
        "token": "",
        "type": 0,
        "username": "xiaoyang"// 可用
    }
 */
data class WanAndroidLoginInfoUserInfoBean(
        val  admin:Boolean,
        val  chapterTops: List<*>,
        val  coinCount: Int,
        val  collectIds: List<*>,
        val  email: String?,
        val  icon: String?,
        val  id: Int,
        val  nickname: String?,
        val  password: String?,
        val  publicName: String?,
        val  token: String?,
        val  type: Int,
        val  username: String?
)
