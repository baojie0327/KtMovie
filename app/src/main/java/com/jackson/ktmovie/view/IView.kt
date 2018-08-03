package com.jackson.ktmovie.view

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

    /**
     * 热映
     */
    interface IHotShowView{
        fun setData(dataList:MutableList<HotShowBean.SubjectsBean>) // 设置数据
        fun closeDisposable(disposable:Disposable)
    }
}