package com.jackson.ktmovie.apiservice

import io.reactivex.disposables.Disposable

/**
 * MyCallBack  2018-06-29
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 网络请求回调接口
 * @author Jackson
 * @version 1.0.0
 * since 2018 06 29
 */
interface MyCallBack<in T> {
    fun onSuccess(response: T)
    fun onError(header: String, message: String)
    fun onDispose(disposable: Disposable)
}