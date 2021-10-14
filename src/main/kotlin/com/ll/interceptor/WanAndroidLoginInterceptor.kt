package com.ll.interceptor

import com.ll.ErrorCode
import com.ll.bean.WanAndroidLoginInfoWrapper
import com.ll.util.UIUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 *  定制消息拦截器
 */
abstract class WanAndroidLoginInterceptor<T>(private val showLoading: Boolean = false) : Observer<WanAndroidLoginInfoWrapper<T>> {
    abstract fun onSuccess(data: T)

    abstract fun onFailure(errorCode: Int, errorMsg: String)

    override fun onSubscribe(d: Disposable) {
        if (showLoading) {
            UIUtil.showLoading()
        }
    }

    override fun onNext(t: WanAndroidLoginInfoWrapper<T>) {
        val errorCode = t.errorCode
        when (errorCode) {
            0 -> {
                if (t.data == null) {
                    onFailure(ErrorCode.userInfoError, "获取的用户信息为空请检查：code = $errorCode, msg = ${t.errorMsg}")
                } else {
                    onSuccess(t.data)
                }
            }
            else -> {
                onFailure(errorCode, t.errorMsg)
            }
        }
    }

    override fun onComplete() {
        if (showLoading) {
            UIUtil.cancelLoading()
        }
    }

    override fun onError(e: Throwable) {
        if (showLoading) {
            UIUtil.cancelLoading()
        }

        onFailure(ErrorCode.networkError, "网络错误，具体消息如下：\n${e.message}")
    }

}