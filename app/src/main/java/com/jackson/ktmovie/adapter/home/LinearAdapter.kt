package com.jackson.ktmovie.adapter.home

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseViewHolder
import com.jackson.ktmovie.R
import com.jackson.ktmovie.adapter.BaseDelegateAdapter
import com.jackson.ktmovie.bean.HomeBean
import com.jackson.ktmovie.utils.glide.GlideUtils

/**
 * LinearAdapter  2018-08-08
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 08
 */
class LinearAdapter(context: Context, list: List<HomeBean.DataBean.ItemsBean>, layoutHelper: LayoutHelper, layoutId: Int, count: Int, viewTypeItem: Int) : BaseDelegateAdapter<HomeBean.DataBean.ItemsBean, BaseViewHolder>(context, list, layoutHelper, layoutId, count, viewTypeItem) {
    override fun convert(helper: BaseViewHolder, item: HomeBean.DataBean.ItemsBean?, position: Int) {
        // 图片
        GlideUtils.loadUrlImage(mContext,
                "http:" + item!!.item!!.mainImg,
                helper.getView(R.id.img_choice_menu))

        // 加载文字
        helper.setText(R.id.tv_choice_name, item.item!!.skuName)
        helper.setText(R.id.tv_choice_factory, item.item!!.factoryName)

        helper.getView<LinearLayout>(R.id.ll_choice_item).setOnClickListener { view -> mOnItemClickListener!!.onItemClick(view, position) }

        helper.getView<ImageView>(R.id.img_choice_menu).setOnClickListener { view -> mOnItemChildClickListener!!.onItemChildClick(view, position) }

        helper.getView<TextView>(R.id.tv_choice_name).setOnClickListener { view -> mOnItemChildClickListener!!.onItemChildClick(view, position) }
    }
}