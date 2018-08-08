package com.jackson.ktmovie.adapter.home

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseViewHolder
import com.jackson.ktmovie.R
import com.jackson.ktmovie.adapter.BaseDelegateAdapter
import com.jackson.ktmovie.bean.HomeBean
import com.jackson.ktmovie.utils.glide.GlideUtils

/**
 * ColumnAdapter  2018-08-08
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 08
 */
class ColumnAdapter(context: Context, list: List<HomeBean.DataBean.ItemsBean>, layoutHelper: LayoutHelper, layoutId: Int, count: Int, viewTypeItem: Int) : BaseDelegateAdapter<HomeBean.DataBean.ItemsBean, BaseViewHolder>(context, list, layoutHelper, layoutId, count, viewTypeItem) {
    override fun convert(helper: BaseViewHolder, item: HomeBean.DataBean.ItemsBean?, position: Int) {
        GlideUtils.loadUrlImage(mContext,
                "http:" + item!!.item!!.mainImg,
                helper.getView<ImageView>(R.id.img_column_menu) as ImageView)
        helper.setText(R.id.tv_column_menu, item.item!!.skuName)

        helper.getView<LinearLayout>(R.id.ll_column_item).setOnClickListener(View.OnClickListener { view -> mOnItemClickListener!!.onItemClick(view, position) })
    }
}