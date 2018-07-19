package com.jackson.ktmovie.apiservice

import com.jackson.ktmovie.bean.InTheatersBean
import com.jackson.ktmovie.utils.Constant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * JsNetworkService  2018-06-29
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 网络请求服务类
 * @author Jackson
 * @version 1.0.0
 * since 2018 06 29
 */
class JsNetworkService private constructor(retrofitClient: RetrofitClient) {

     var mINetworkService: INetworkService

    init {
        mINetworkService = retrofitClient.create(JsNetworkService.INetworkService::class.java)
    }

    /**
     * 单例模式获取实例
     */
    companion object {
        val instance = JsNetworkService(RetrofitClient(Constant.baseUrl))
    }


    /**
     * 网络请求服务接口
     */
    interface INetworkService {

        @GET("v2/movie/in_theaters")
        fun getInTheatersData(@QueryMap parmMap:Map<String,String>):Observable<InTheatersBean>


    }
}