package com.jackson.ktmovie.model

import com.jackson.ktmovie.apiservice.JsNetworkService
import com.jackson.ktmovie.apiservice.MyCallBack
import com.jackson.ktmovie.bean.HomeBean
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * HomeModel  2018-08-06
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 06
 */
class HomeModel(private var mJsNetworkService: JsNetworkService) :IModel.IHomeModel{

    override fun getData(paraMap: Map<String, String>, callBack: MyCallBack<HomeBean>) =
            mJsNetworkService.getINetworkService()
                    .getHomeData(paraMap)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object :Observer<HomeBean>{
                        override fun onComplete() =Unit

                        override fun onNext(t: HomeBean)=
                            callBack.onSuccess(t)


                        override fun onSubscribe(d: Disposable) {
                            var disPosable: Disposable = d
                            callBack.onDispose(disPosable)
                        }

                        override fun onError(e: Throwable) =
                                callBack.onError("Server Error", "服务器异常，请稍后再试")

                    })



}