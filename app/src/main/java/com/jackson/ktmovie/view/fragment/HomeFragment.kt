package com.jackson.ktmovie.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.jackson.ktmovie.R
import org.jetbrains.anko.find

/**
 * HomeFragment  2018-06-19
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 首页
 * @author Jackson
 * @version 1.0.0
 * since 2018 06 19
 */
class HomeFragment : Fragment() {

    private var rootView: View? = null
    private lateinit var llBack: LinearLayout  // 返回键，需要隐藏
    private lateinit var tvTitle: TextView   // title
    private lateinit var mRecyclerView: RecyclerView



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater!!.inflate(R.layout.fragment_home_layout, null)
            initView()


        }
        return rootView
    }

    /**
     * 初始化界面
     */
    private fun initView() {
        // 隐藏返回键
        llBack = rootView!!.find(R.id.ll_back)
        llBack.visibility = View.INVISIBLE
        // 设置title
        tvTitle = rootView!!.find(R.id.tv_head_title)
        tvTitle.text = "正在上映"
        // 设置RecyclerView
        mRecyclerView = rootView!!.find(R.id.recyclerView)
        var layoutManger: LinearLayoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = layoutManger
    }









    /**
     * 伴生对象，提供Fragment的实例
     */
    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }

    }


}