package com.jackson.ktmovie.model

import com.jackson.ktmovie.apiservice.MyCallBack
import com.jackson.ktmovie.bean.InTheatersBean
import retrofit2.http.QueryMap

/**
 * IModel  2018-06-29
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 06 29
 */
class IModel {

    /**
     * 正在上映
     */
    interface IInTheatersModel {
        fun getData(@QueryMap paraMap: MutableMap<String, String>, callBack: MyCallBack<InTheatersBean>)
    }


}