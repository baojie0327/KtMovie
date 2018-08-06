package com.jackson.ktmovie.apiservice

import com.jackson.ktmovie.bean.HomeBean
import com.jackson.ktmovie.bean.HotShowBean
import io.reactivex.Observable
import retrofit2.http.*

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
class JsNetworkService (retrofitClient: RetrofitClient) {

    var mINetworkService: INetworkService

    init {
        mINetworkService = retrofitClient.create(JsNetworkService.INetworkService::class.java)
    }

    /**
     * 单例模式获取实例
     */
   /* companion object {
        val instance = JsNetworkService(RetrofitClient(Constant.baseUrl))
    }*/
    fun getINetworkService():INetworkService = mINetworkService


    /**
     * 网络请求服务接口
     */
    interface INetworkService {

        /**
         * 首页
         */
        @POST("home/content")
        @FormUrlEncoded
        fun getHomeData(@FieldMap parmMap: Map<String, String>): Observable<HomeBean>
        /**
         * 热映
         */
        @GET("v2/movie/in_theaters")
        fun getHotShowData(@QueryMap parmMap: Map<String, String>): Observable<HotShowBean>


    }
}