package com.jackson.ktmovie.adapter

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
abstract class BaseDelegateAdapter<in T, K : BaseViewHolder>
(protected var mContext: Context, private val mDataList: List<T>,
 private val mLayoutHelper: LayoutHelper, private var mLayoutId: Int,private var mCount: Int,private var mViewTypeItem: Int) : DelegateAdapter.Adapter<K>() {

    protected var mOnItemClickListener: OnItemClickListener? = null   // item点击监听
    protected var mOnItemChildClickListener: OnItemChildClickListener? = null // child item 点击监听


    /**
     * 构造方法
     */
   /* constructor(context: Context, list: List<T>, layoutHelper: LayoutHelper, layoutId: Int, count: Int, viewTypeItem: Int) : this() {
        this.mContext = context
        this.mDataList = list
        this.mLayoutHelper = layoutHelper
        this.mLayoutId = layoutId
        this.mCount = count
        this.mViewTypeItem = viewTypeItem
    }*/

    override fun onCreateLayoutHelper(): LayoutHelper? = mLayoutHelper

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): K? {
        if (viewType == mViewTypeItem) {
            return BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, parent, false)) as K
        }
        return null
    }

    override fun onBindViewHolder(holder: K, position: Int) =
            convert(holder, getItem(position), position)

    override fun getItemCount(): Int = mCount

    override fun getItemViewType(position: Int): Int = mViewTypeItem

    protected abstract fun convert(helper: K, item: T?, position: Int)

    private fun getItem(@IntRange(from = 0) position: Int): T? =
            if (position < mDataList!!.size)
                mDataList!![position]
            else
                null


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

