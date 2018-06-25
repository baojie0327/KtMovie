package com.jackson.ktmovie.utils

import android.content.Context
import android.widget.Toast

/**
 * ExpandUtil  2018-06-19
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 扩展函数工具类
 * 存放所有的扩展 函数
 * @author Jackson
 * @version 1.0.0
 * since 2018 06 19
 */
class ExpandUtil {

    /**
     * 扩展Context，添加toast
     */
    fun Context.toast(message:String,duration:Int=Toast.LENGTH_SHORT) =
            Toast.makeText(this,message,duration).show()


}