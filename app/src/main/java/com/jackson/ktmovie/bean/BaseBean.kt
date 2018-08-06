package com.jackson.ktmovie.bean

import java.io.Serializable

/**
 * BaseBean  2018-07-02
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 基础实体类
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 02
 */
open class BaseBean<T> : Serializable {

    // Moive
    var code: String = ""
    var msg: String = ""
    var request: String = ""
    var count: Int = 0
    var start: Int = 0
    var total: Int = 0
    var title: String? = null
    var subjects: MutableList<T>? = null
    var data: List<T>? = null

}