package com.jackson.ktmovie.adapter.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jackson.ktmovie.R
import com.jackson.ktmovie.bean.HomeBean
import com.jackson.ktmovie.utils.glide.GlideUtils

/**
 * ItemHorizonAdapter  2018-08-08
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 08
 */
class ItemHorizonAdapter(layoutResId: Int, data: MutableList<HomeBean.DataBean.ItemsBean>?) : BaseQuickAdapter<HomeBean.DataBean.ItemsBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder?, item: HomeBean.DataBean.ItemsBean?) {
        GlideUtils.loadUrlImage(mContext, "http:" + item!!.item!!.mainImg, helper!!.getView(R.id.img_prepare_menu))
        helper.setText(R.id.tv_prepare_menu, item!!.item!!.skuName)
    }
}