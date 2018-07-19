package com.jackson.ktmovie.dagger.component

import com.jackson.ktmovie.dagger.module.InTheatersModule
import com.jackson.ktmovie.presenter.InTheatersPresenter
import com.jackson.ktmovie.view.fragment.HomeFragment
import dagger.Component
import javax.inject.Singleton

/**
 * InTheatersComponent  2018-07-02
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 正在上映
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 02
 */
@Singleton
@Component(modules = arrayOf(InTheatersModule::class))
interface InTheatersComponent {
    fun inject(homeFragment: HomeFragment)
    fun inject(inTheatersPresenter: InTheatersPresenter)
}