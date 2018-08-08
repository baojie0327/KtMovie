package com.jackson.ktmovie.adapter.home

import android.content.Context
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseViewHolder
import com.jackson.ktmovie.adapter.BaseDelegateAdapter

/**
 * StickyAdapter  2018-08-08
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 08
 */
class StickyAdapter(context: Context, list: List<String>, layoutHelper: LayoutHelper, layoutId: Int, count: Int, viewTypeItem: Int) : BaseDelegateAdapter<String, BaseViewHolder>(context, list, layoutHelper, layoutId, count, viewTypeItem) {

    override fun convert(helper: BaseViewHolder, item: String?, position: Int) =Unit

}