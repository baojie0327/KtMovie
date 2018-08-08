package com.jackson.ktmovie.adapter.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseViewHolder
import com.jackson.ktmovie.R
import com.jackson.ktmovie.adapter.BaseDelegateAdapter

/**
 * FloatAdapter  2018-08-08
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 08
 */
class FloatAdapter(context: Context, list: List<String>, layoutHelper: LayoutHelper, layoutId: Int, count: Int, viewTypeItem: Int) : BaseDelegateAdapter<String, BaseViewHolder>(context, list, layoutHelper, layoutId, count, viewTypeItem) {
    override fun convert(helper: BaseViewHolder, item: String?, pos: Int) {
        val layoutParams = ViewGroup.LayoutParams(200, 200)
        helper.itemView.layoutParams = layoutParams
        helper.getView<LinearLayout>(R.id.ll_listener).setOnClickListener(View.OnClickListener { view -> mOnItemClickListener!!.onItemClick(view, pos) })
    }
}