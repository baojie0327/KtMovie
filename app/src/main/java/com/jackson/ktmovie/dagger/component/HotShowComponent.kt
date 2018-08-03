package com.jackson.ktmovie.dagger.component

import com.jackson.ktmovie.dagger.module.HotShowModule
import com.jackson.ktmovie.presenter.HotShowPresenter
import com.jackson.ktmovie.view.fragment.HotShowFragment
import dagger.Component
import javax.inject.Singleton

/**
 * InTheatersComponent  2018-07-02
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 热映
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 02
 */
@Singleton
@Component(modules = arrayOf(HotShowModule::class))
interface HotShowComponent {
    fun inject(hotShowFragment: HotShowFragment)
    fun inject(hotShowPresenter: HotShowPresenter)
}