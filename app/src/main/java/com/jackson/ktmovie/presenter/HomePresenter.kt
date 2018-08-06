package com.jackson.ktmovie.presenter

import com.jackson.ktmovie.apiservice.MyCallBack
import com.jackson.ktmovie.bean.HomeBean
import com.jackson.ktmovie.dagger.component.DaggerHomeComponent
import com.jackson.ktmovie.dagger.module.HomeModule
import com.jackson.ktmovie.model.IModel
import com.jackson.ktmovie.view.IView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * HomePresenter  2018-08-06
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 06
 */
class HomePresenter(iHomeView: IView.IHomeView) {

    private var mIHomeView: IView.IHomeView? = null

    @Inject
    lateinit var mIHomeModel: IModel.IHomeModel

    init {
        mIHomeView = iHomeView
        inject()
    }

    private fun inject() =
            DaggerHomeComponent.builder()
            .homeModule(HomeModule())
            .build()
            .inject(this)

    fun getData() {
        // 参数
        var paraMap: MutableMap<String, String> = mutableMapOf()
        paraMap.put("phoneNumber", "18310083556")
        paraMap.put("clientType", "android")
        paraMap.put("osVersion", "8.1.0")
        paraMap.put("model", "MI%208&clientVersion=2.4.0&networkType=wifi&uuid=869071035544817")
        paraMap.put("brand", "Xiaomi")
        paraMap.put("lon", "116.5157")
        paraMap.put("lat", "39.933067")
        // request
        mIHomeModel.getData(paraMap, object : MyCallBack<HomeBean> {
            override fun onSuccess(response: HomeBean) {
                if (response.code == "1") {
                    mIHomeView!!.setData(response.data!!)
                }
            }

            override fun onError(header: String, message: String) = Unit


            override fun onDispose(disposable: Disposable) =
                    mIHomeView!!.closeDispose(disposable)

        })


    }


}