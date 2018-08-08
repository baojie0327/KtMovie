package com.jackson.ktmovie.adapter.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.chad.library.adapter.base.BaseViewHolder
import com.jackson.ktmovie.R
import com.jackson.ktmovie.adapter.BaseDelegateAdapter
import com.jackson.ktmovie.bean.HomeBean
import com.jackson.ktmovie.utils.glide.GlideUtils

/**
 * StaggeredAdapter  2018-08-08
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 08
 */
class StaggeredAdapter(context: Context, list: List<HomeBean.DataBean.ItemsBean>, layoutHelper: LayoutHelper, layoutId: Int, count: Int, viewTypeItem: Int) : BaseDelegateAdapter<HomeBean.DataBean.ItemsBean, BaseViewHolder>(context, list, layoutHelper, layoutId, count, viewTypeItem) {
    override fun convert(helper: BaseViewHolder, item: HomeBean.DataBean.ItemsBean?, position: Int) {
        helper.getView<LinearLayout>(R.id.ll_staggered_item).post(Runnable {
            //     LogUtil.d(helper.getView(R.id.ll_staggered_item).getWidth()+"  "+helper.getView(R.id.ll_staggered_item).getHeight());
        })

        val layoutParams = VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 750)
        //   LogUtil.d("width==" + layoutParams.width + " height==" + layoutParams.height);
        layoutParams.height = 750 + position % 3 * 30
        /*if (position % 2 == 0) {
            layoutParams.height = 750 + position % 3 * 30;
        } else {
            layoutParams.height = 750 + position % 2 * 5;
        }*/
        helper.itemView.layoutParams = layoutParams
        // 图片
        GlideUtils.loadUrlImage(mContext,
                "http:" + item!!.item!!.mainImg,
                helper.getView<ImageView>(R.id.img_staggered_menu) as ImageView)
        // 加载文字
        helper.setText(R.id.tv_staggered_menu, item!!.item!!.skuName)
        helper.setText(R.id.tv_staggered_factory, item!!.item!!.factoryName)
        helper.setText(R.id.tv_staggered_shop, item!!.item!!.shopName)

        helper.getView<LinearLayout>(R.id.ll_staggered_item).setOnClickListener(View.OnClickListener { view -> mOnItemClickListener!!.onItemClick(view, position) })
    }
}