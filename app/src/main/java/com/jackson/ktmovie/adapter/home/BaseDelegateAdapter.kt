package com.jackson.ktmovie.adapter.home

/**
 * BaseDelegateAdapter  2018-07-19
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.content.Context
import android.support.annotation.IntRange
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseViewHolder

/**
 * class description here
 *
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 19
 */
abstract class BaseDelegateAdapter<T, K : BaseViewHolder>
(protected var mContext: Context, private val mDataList: List<T>, private val mLayoutHelper: LayoutHelper, lauoutId: Int, count: Int, viewTypeItem: Int) : DelegateAdapter.Adapter<K>() {

    private var mCount = -1
    private var mLayoutId = -1
    private var mViewTypeItem = -1

    protected var mOnItemClickListener: OnItemClickListener? = null   // item点击监听
    protected var mOnItemChildClickListener: OnItemChildClickListener? = null // child item 点击监听

    init {
        this.mLayoutId = lauoutId
        this.mCount = count
        this.mViewTypeItem = viewTypeItem
    }

    override fun onCreateLayoutHelper(): LayoutHelper = mLayoutHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): K? =//   this.mContext = parent.getContext();
            if (viewType == mViewTypeItem) {
                BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, parent, false)) as K
            } else null

    override fun onBindViewHolder(holder: K, position: Int) {
        convert(holder, getItem(position), position)
    }


    override fun getItemCount(): Int = mCount

    /**
     * 必须重写不然会出现滑动不流畅的情况
     */
    override fun getItemViewType(position: Int): Int {
        return mViewTypeItem
    }

    protected abstract fun convert(helper: K, item: T?, position: Int)

    fun getItem(@IntRange(from = 0) position: Int): T? {
        return if (position < mDataList.size)
            mDataList[position]
        else
            null
    }

    /**
     * 监听接口
     */
    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    interface OnItemChildClickListener {
        fun onItemChildClick(view: View, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mOnItemClickListener = listener
    }

    fun setOnItemChildClickListener(listener: OnItemChildClickListener) {
        mOnItemChildClickListener = listener
    }

}

