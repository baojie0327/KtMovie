package com.jackson.ktmovie.dagger.module

import com.jackson.ktmovie.model.IModel
import com.jackson.ktmovie.model.InTheatersModel
import com.jackson.ktmovie.presenter.InTheatersPresenter
import com.jackson.ktmovie.view.IView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * InTheatersModule  2018-07-02
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 02
 */
@Module
class InTheatersModule {

    private lateinit var inTheatersView: IView.IInTheatersView

    /**
     * 空的构造方法，在presenter使用
     */
    constructor() {

    }

    /**
     * 在Fragment使用
     */
    constructor(view: IView.IInTheatersView) {
        this.inTheatersView = view
    }


    /**
     * 提供Presenter，正在上映
     */
    @Provides
    @Singleton
    fun provideInTheatersPresenter(iIInTheatersView: IView.IInTheatersView): InTheatersPresenter =
            InTheatersPresenter(iIInTheatersView)

    /**
     * 提供InTheatersModel，正在上映
     */
    @Provides
    @Singleton
    fun provideInTheatersModel(): IModel.IInTheatersModel =
            InTheatersModel()


    /**
     * 提供IInTheatersView，正在上映
     */
    @Provides
    @Singleton
    fun provideIInTheatersView(): IView.IInTheatersView =
            inTheatersView


}