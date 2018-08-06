package com.jackson.ktmovie.dagger.module

import com.jackson.ktmovie.apiservice.JsNetworkService
import com.jackson.ktmovie.apiservice.RetrofitClient
import com.jackson.ktmovie.model.HomeModel
import com.jackson.ktmovie.model.IModel
import com.jackson.ktmovie.presenter.HomePresenter
import com.jackson.ktmovie.utils.Constant
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
class HomeModule {

    private lateinit var homeView: IView.IHomeView

    /**
     * 空的构造方法，在presenter使用
     */
    constructor() {

    }

    /**
     * 在Fragment使用
     */
    constructor(view: IView.IHomeView) {
        this.homeView = view
    }


    /**
     * 提供Presenter，正在上映
     */
    @Provides
    @Singleton
    fun provideInTheatersPresenter(iIHomeView: IView.IHomeView): HomePresenter =
            HomePresenter(iIHomeView)

    /**
     * IHomeModel
     */
    @Provides
    @Singleton
    fun provideHomeModel(mJsNetworkService: JsNetworkService): IModel.IHomeModel =
            HomeModel(mJsNetworkService)


    /**
     * JsNetworkService
     */
    @Provides
    @Singleton
    fun provideJsNetworkService(retrofitClient: RetrofitClient) =
            JsNetworkService(retrofitClient)

    /**
     * JsNetworkService
     */
    @Provides
    @Singleton
    fun provideRetrofitClient() =
            RetrofitClient(Constant.baseUrlJD)


    /**
     * 提供IInTheatersView，正在上映
     */
    @Provides
    @Singleton
    fun provideIHomeView(): IView.IHomeView =
            homeView


}