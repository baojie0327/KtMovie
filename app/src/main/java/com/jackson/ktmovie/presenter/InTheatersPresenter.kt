package com.jackson.ktmovie.presenter

import com.jackson.ktmovie.apiservice.MyCallBack
import com.jackson.ktmovie.bean.InTheatersBean
import com.jackson.ktmovie.dagger.component.DaggerInTheatersComponent
import com.jackson.ktmovie.dagger.module.InTheatersModule
import com.jackson.ktmovie.model.IModel
import com.jackson.ktmovie.view.IView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * InTheatersPresenter  2018-07-02
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 02
 */
class InTheatersPresenter(iInTheatersView: IView.IInTheatersView) {

    private var mIInTheatersView: IView.IInTheatersView? = null

    @Inject
     lateinit var mInTheatersModel:IModel.IInTheatersModel

    /**
     * 初始化
     */
    init {
        mIInTheatersView = iInTheatersView
        inject()
    }

    /**
     * 获取数据
     */
    fun getData() {
       /* var city: String = ""
        try {
            city = URLEncoder.encode("北京", "utf-8")
        } catch (e: Exception) {
        }*/
        val parmMap:MutableMap<String,String> = mutableMapOf()
        parmMap.put("city", "北京")

        mInTheatersModel!!.getData(parmMap, object : MyCallBack<InTheatersBean> {
           // 成功回调
            override fun onSuccess(response: InTheatersBean) {
                if (response.code.isEmpty()) {
                    mIInTheatersView!!.setData(response.subjects!!)
                }
            }

            override fun onError(header: String, message: String) = Unit

            override fun onDispose(disposable: Disposable) =
                    mIInTheatersView!!.closeDisposable(disposable)

        })
    }

    /**
     * 完成mInTheatersModel的注入操作
     */
    private fun inject()=
        DaggerInTheatersComponent.builder()
                .inTheatersModule(InTheatersModule())
                .build()
                .inject(this)




}