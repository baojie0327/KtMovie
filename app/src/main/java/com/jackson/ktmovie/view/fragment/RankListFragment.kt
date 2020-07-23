package com.jackson.ktmovie.view.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.jackson.ktmovie.R

import com.jackson.ktmovie.view.activity.ConstraintLayoutActivity
import com.jackson.ktmovie.view.activity.kotlinbase.KotlinBaseActivity
import org.jetbrains.anko.constraint.layout._ConstraintLayout
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

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
    private lateinit var btn_kotlin: Button
    private lateinit var btn_constraintLayout: Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_ranklist_layout, null)

        }
        btn_kotlin = rootView!!.find(R.id.btn_kotlin)
        btn_constraintLayout= rootView!!.find(R.id.btn_cons)
        btn_kotlin.setOnClickListener { startActivity<KotlinBaseActivity>() }
        btn_constraintLayout.setOnClickListener { startActivity<ConstraintLayoutActivity>() }






        return rootView
    }

    /**
     * 伴生对象，提供Fragment实例
     */
    companion object {
        fun newInstance(): RankListFragment {
            val fragment = RankListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

}