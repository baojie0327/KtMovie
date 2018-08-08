package com.jackson.ktmovie.adapter.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseViewHolder
import com.jackson.ktmovie.R
import com.jackson.ktmovie.adapter.BaseDelegateAdapter
import com.jackson.ktmovie.bean.HomeBean
import com.jackson.ktmovie.utils.glide.GlideUtils

/**
 * OnePlusNAdapter  2018-08-08
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 08
 */
class OnePlusNAdapter(context: Context, list: List<HomeBean.DataBean.ItemsBean>, layoutHelper: LayoutHelper, layoutId: Int, count: Int, viewTypeItem: Int) : BaseDelegateAdapter<HomeBean.DataBean.ItemsBean, BaseViewHolder>(context, list, layoutHelper, layoutId, count, viewTypeItem) {
    override fun convert(helper: BaseViewHolder, item: HomeBean.DataBean.ItemsBean?, position: Int) {
        if (position == 0) {
            val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            layoutParams.setMargins(20, 20, 20, 20)
            helper.getView<ImageView>(R.id.img_oneplusn_menu).layoutParams = layoutParams
        }
        GlideUtils.loadUrlImage(mContext,
                "http:" + item!!.item!!.mainImg,
                helper.getView<ImageView>(R.id.img_oneplusn_menu) as ImageView)
        //  helper.setText(R.id.tv_oneplusn_menu,item.getItem().getSkuName());

        helper.getView<LinearLayout>(R.id.ll_oneplusn_item).setOnClickListener(View.OnClickListener { view -> mOnItemClickListener!!.onItemClick(view, position) })
    }

}