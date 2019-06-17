package com.jackson.ktmovie.presenter

import com.jackson.ktmovie.apiservice.MyCallBack
import com.jackson.ktmovie.bean.HotShowBean
import com.jackson.ktmovie.dagger.component.DaggerHotShowComponent
import com.jackson.ktmovie.dagger.module.HotShowModule
import com.jackson.ktmovie.model.IModel
import com.jackson.ktmovie.utils.Constant
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
class HotShowPresenter(iIHotShowView: IView.IHotShowView) {

    private var mIHotShowView: IView.IHotShowView? = null

    @Inject
    lateinit var mIHotShowModel: IModel.IHotShowModel

    /**
     * 初始化
     */
    init {
        mIHotShowView = iIHotShowView
        inject()
    }

    /**
     *
     */
    fun getData(type: Int, start: Int) {
        /* var city: String = ""
         try {
             city = URLEncoder.encode("北京", "utf-8")
         } catch (e: Exception) {
         }*/
        val paraMap: MutableMap<String, String> = mutableMapOf()
        paraMap.put("apikey", "0df993c66c0c636e29ecbb5344252a4a")
        paraMap.put("city", "北京")
        paraMap.put("start", start.toString())
        paraMap.put("count", Constant.PAGE_COUNT.toString())

        mIHotShowModel!!.getData(paraMap, object : MyCallBack<HotShowBean> {
            // 成功回调
            override fun onSuccess(response: HotShowBean) {
                if (response.code.isEmpty()) {
                    when (type) {
                        0 -> mIHotShowView!!.setData(response.subjects!!)
                        1 -> mIHotShowView!!.onRefresh(response.subjects!!)
                        2 -> mIHotShowView!!.onLoadMore(response.subjects!!)
                    }

                }
            }

            override fun onError(header: String, message: String) = Unit

            override fun onDispose(disposable: Disposable) =
                    mIHotShowView!!.closeDisposable(disposable)

        })
    }

    /**
     * 完成mInTheatersModel的注入操作
     */
    private fun inject() =
            DaggerHotShowComponent.builder()
                    .hotShowModule(HotShowModule())
                    .build()
                    .inject(this)


}