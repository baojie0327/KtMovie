package com.jackson.ktmovie.adapter.home

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseViewHolder
import com.jackson.ktmovie.R
import com.jackson.ktmovie.adapter.BaseDelegateAdapter
import com.jackson.ktmovie.utils.CommonMethod
import com.jackson.ktmovie.utils.GlideImageLoader
import com.youth.banner.Banner

/**
 * BannerAdapter  2018-08-06
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 06
 */
class BannerAdapter : BaseDelegateAdapter<String, BaseViewHolder> {


    private var imgUrlList: List<String> = arrayListOf()

    /**
     * 构造函数
     */
    constructor(mContext: Context, mDataList: List<String>, mLayoutHelper: LayoutHelper, mLayoutId: Int, mCount: Int, mViewTypeItem: Int) : super(mContext, mDataList, mLayoutHelper, mLayoutId, mCount, mViewTypeItem) {
        imgUrlList = mDataList
    }

    override fun convert(helper: BaseViewHolder, item: String?, pos: Int) {
        var mBanner: Banner = helper.getView<Banner>(R.id.banner)
        // 设置图片加载器
        mBanner.setImageLoader(GlideImageLoader())
        var layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.height = CommonMethod.convertDpToPixel(mContext!!, 200.toFloat())
        mBanner.layoutParams = layoutParams
        mBanner.setImages(imgUrlList)
        mBanner.start()
        // 监听
        mBanner.setOnBannerListener { position -> mOnItemClickListener!!.onItemClick(helper.itemView, position) }

    }


}