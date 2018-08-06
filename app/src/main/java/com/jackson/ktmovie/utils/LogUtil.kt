package com.jackson.ktmovie.utils

import android.util.Log


/*
* LogUtil  2016-11-10
* Copyright (c) 2016 HYB Co.Ltd. All right reserved.
*/
/*
* 全局的Log调试信息配置
* @author Jackson
* @version 1.0.0
* since 2016-11-10
*/
object LogUtil {
    //控制是否打印调试信息
    private val TAG = "hbj--"

    fun d(msg: String?) {
        if (msg != null) {
            if (Constant.LOG_DEBUG) {
                Log.d(TAG, msg)
            }
        }
    }

    fun i(msg: String?) {
        if (msg != null) {
            if (Constant.LOG_DEBUG)
                Log.i(TAG, msg)
        }
    }

    fun w(msg: String?) {
        if (msg != null) {
            if (Constant.LOG_DEBUG)
                Log.w(TAG, msg)
        }
    }

    fun e(msg: String?) {
        if (msg != null) {
            if (Constant.LOG_DEBUG)
                Log.e(TAG, msg)
        }
    }

    fun v(msg: String?) {
        if (msg != null) {
            if (Constant.LOG_DEBUG)
                Log.v(TAG, msg)
        }
    }
}