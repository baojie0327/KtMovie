package com.jackson.ktmovie.adapter.home

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseViewHolder
import com.jackson.ktmovie.R
import com.jackson.ktmovie.adapter.BaseDelegateAdapter
import com.jackson.ktmovie.bean.HomeBean

/**
 * HorizontalAdapter  2018-08-08
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 08
 */
class HorizontalAdapter(context: Context, list: List<HomeBean.DataBean.ItemsBean>, layoutHelper: LayoutHelper, layoutId: Int, count: Int, viewTypeItem: Int) : BaseDelegateAdapter<HomeBean.DataBean.ItemsBean, BaseViewHolder>(context, list, layoutHelper, layoutId, count, viewTypeItem) {

    private var dataList: MutableList<HomeBean.DataBean.ItemsBean> = mutableListOf()

    init {
        this.dataList = list.toMutableList()
    }

    override fun convert(helper: BaseViewHolder, item: HomeBean.DataBean.ItemsBean?, position: Int) {
        val recyclerView: RecyclerView = helper.getView(R.id.prepareRecyclerView)
        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(mContext)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = linearLayoutManager
        val mItemHorizonAdapter: ItemHorizonAdapter = ItemHorizonAdapter(R.layout.item_item_home_horizon_layout, dataList)
        recyclerView.adapter = mItemHorizonAdapter
        // 监听
        mItemHorizonAdapter.setOnItemClickListener { adapter, view, position ->
            mOnItemClickListener!!.onItemClick(view, position)
        }

    }
}