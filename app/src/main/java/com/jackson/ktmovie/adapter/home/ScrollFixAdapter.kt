package com.jackson.ktmovie.adapter.home

import android.content.Context
import android.widget.ImageView
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseViewHolder
import com.jackson.ktmovie.R
import com.jackson.ktmovie.adapter.BaseDelegateAdapter

/**
 * ScrollFixAdapter  2018-08-07
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 可选固定布局,ScrollFixLayoutHelper
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 07
 */
class ScrollFixAdapter(context: Context, list: List<String>, layoutHelper: LayoutHelper, layoutId: Int, count: Int, viewTypeItem: Int) : BaseDelegateAdapter<String, BaseViewHolder>(context, list, layoutHelper, layoutId, count, viewTypeItem) {


    override fun convert(helper: BaseViewHolder, item: String?, pos: Int) =
            helper.getView<ImageView>(R.id.img_fix).setOnClickListener{ view->
                mOnItemClickListener!!.onItemClick(view,pos)
            }

}