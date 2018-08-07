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
 * GridLayoutHelper
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 07
 */
class GridMenuAdapter(context: Context, list: List<HomeBean.DataBean.ItemsBean>, layoutHelper: LayoutHelper, layoutId: Int, count: Int, viewTypeItem: Int) : BaseDelegateAdapter<HomeBean.DataBean.ItemsBean, BaseViewHolder>(context, list, layoutHelper, layoutId, count, viewTypeItem) {


    override fun convert(helper: BaseViewHolder, item: HomeBean.DataBean.ItemsBean?, position: Int) {
        // 图片
        GlideUtils.loadUrlImage(mContext, "http:" + item!!.item!!.img, helper.getView<ImageView>(R.id.img_grid_menu) as ImageView)
        // 文字
        helper.setText(R.id.tv_grid_menu, item.name)
        // 监听
        helper.getView<LinearLayout>(R.id.ll_grid_menu).setOnClickListener { view ->
            mOnItemClickListener!!.onItemClick(view, position)
        }
        helper.getView<ImageView>(R.id.img_grid_menu).setOnClickListener{view->
            mOnItemChildClickListener!!.onItemChildClick(view,position)
        }
    }
}