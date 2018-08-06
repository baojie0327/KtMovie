package com.jackson.ktmovie.view

import com.jackson.ktmovie.bean.HomeBean
import com.jackson.ktmovie.bean.HotShowBean
import io.reactivex.disposables.Disposable

/**
 * IView  2018-07-02
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 02
 */
class IView {

    interface IHomeView {
        fun setData(mDataList: List<HomeBean.DataBean>)  // 网络请求成功后设置数据
        fun onRefresh(mDataList: List<HomeBean.DataBean>)  // refresh
        fun closeDispose(disposable: Disposable)
    }

    /**
     * 热映
     */
    interface IHotShowView {
        fun setData(dataList: MutableList<HotShowBean.SubjectsBean>) // 设置数据
        fun onRefresh(mDataList: List<HotShowBean.SubjectsBean>)  // 刷新
        fun onLoadMore(mDataList: List<HotShowBean.SubjectsBean>)  // 加载数据
        fun closeDisposable(disposable: Disposable)
    }
}