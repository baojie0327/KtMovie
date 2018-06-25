package com.jackson.ktmovie.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jackson.ktmovie.R

/**
 * RankListFragment  2018-06-19
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 榜单
 * @author Jackson
 * @version 1.0.0
 * since 2018 06 19
 */
class RankListFragment : Fragment() {

    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater!!.inflate(R.layout.fragment_ranklist_layout, null)
        }
        return rootView
    }

    /**
     * 伴生对象，提供Fragment实例
     */
    companion object {
        fun newInstance():RankListFragment{
            val fragment=RankListFragment()
            val bundle=Bundle()
            fragment.arguments=bundle
            return fragment
        }
    }

}