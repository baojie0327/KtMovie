package com.jackson.ktmovie.adapter.home

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseViewHolder
import com.jackson.ktmovie.R
import com.jackson.ktmovie.adapter.BaseDelegateAdapter
import com.jackson.ktmovie.bean.HomeBean
import com.jackson.ktmovie.utils.glide.GlideUtils


/**
 * GridMenuAdapter  2018-08-07
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * GridLayoutHelper,SpanSize
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 07
 */
class GridMenuSpanAdapter(context: Context, list: List<HomeBean.DataBean.ItemsBean>, layoutHelper: LayoutHelper, layoutId: Int, count: Int, viewTypeItem: Int) : BaseDelegateAdapter<HomeBean.DataBean.ItemsBean, BaseViewHolder>(context, list, layoutHelper, layoutId, count, viewTypeItem) {


    override fun convert(helper: BaseViewHolder, item: HomeBean.DataBean.ItemsBean?, position: Int) {
        // 图片
        GlideUtils.loadUrlImage(mContext, "http:" + item!!.item!!.img, helper.getView<ImageView>(R.id.img_hotitem_menu) as ImageView)

        // 监听
        helper.getView<LinearLayout>(R.id.ll_hot_item).setOnClickListener { view ->
            mOnItemClickListener!!.onItemClick(view, position)
        }

    }
}