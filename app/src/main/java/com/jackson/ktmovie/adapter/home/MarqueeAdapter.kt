package com.jackson.ktmovie.adapter.home

import android.content.Context
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseViewHolder
import com.jackson.ktmovie.R
import com.jackson.ktmovie.adapter.BaseDelegateAdapter
import com.sunfusheng.marqueeview.MarqueeView

/**
 * MarqueeAdapter  2018-08-07
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 公告,LinearLayoutHelper
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 07
 */
class MarqueeAdapter(context: Context, list: List<String>, layoutHelper: LayoutHelper, layoutId: Int, count: Int, viewTypeItem: Int) : BaseDelegateAdapter<String, BaseViewHolder>(context, list, layoutHelper, layoutId, count, viewTypeItem) {

    var infoList:MutableList<String> = mutableListOf()

    init {
        infoList= list as MutableList<String>
    }


    override fun convert(helper: BaseViewHolder, item: String?, position: Int) {
        val marqueeView:MarqueeView=helper.getView(R.id.marqueeView)
        marqueeView.startWithList(infoList)
        marqueeView.setOnItemClickListener { position, textView ->
            mOnItemClickListener!!.onItemClick(textView,position)
        }
    }
}