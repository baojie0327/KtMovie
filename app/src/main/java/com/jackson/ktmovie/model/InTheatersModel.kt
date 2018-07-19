package com.jackson.ktmovie.model

import com.jackson.ktmovie.apiservice.JsNetworkService
import com.jackson.ktmovie.apiservice.MyCallBack
import com.jackson.ktmovie.bean.InTheatersBean
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * InTheatersModel  2018-07-02
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 正在上映
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 02
 */
class InTheatersModel : IModel.IInTheatersModel {

    override fun getData(paraMap: MutableMap<String, String>, callBack: MyCallBack<InTheatersBean>) =
            JsNetworkService.instance.mINetworkService
                    .getInTheatersData(paraMap)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<InTheatersBean> {
                        /**
                         * 失败
                         */
                        override fun onError(e: Throwable) =
                                callBack.onError("Server Error", "服务器异常，请稍后再试")


                        override fun onSubscribe(d: Disposable) {
                            var disPosable: Disposable = d
                            callBack.onDispose(disPosable)
                        }

                        /**
                         * 完成
                         */
                        override fun onComplete() = Unit

                        /**
                         * 处理中
                         */
                        override fun onNext(t: InTheatersBean) =
                                callBack.onSuccess(t)

                    })
}