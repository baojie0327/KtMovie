package com.jackson.ktmovie.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jackson.ktmovie.R
import com.jackson.ktmovie.bean.HotShowBean
import com.jackson.ktmovie.utils.glide.GlideUtils
import java.text.DecimalFormat

/**
 * HotShowAdapter  2018-07-03
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 热映Adapter
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 03
 */
class HotShowAdapter(layoutResId: Int, data: MutableList<HotShowBean.SubjectsBean>?) : BaseQuickAdapter<HotShowBean.SubjectsBean, BaseViewHolder>(layoutResId, data) {


    override fun convert(helper: BaseViewHolder?, item: HotShowBean.SubjectsBean?) {

        // 加载图片
        GlideUtils.loadUrlImage(mContext, item!!.images!!.medium, helper!!.getView(R.id.img_moive))

        // 电影名
        helper!!.setText(R.id.tv_moive_name, item!!.title)

        // 评分
        helper!!.setRating(R.id.rating_moive, (item!!.rating!!.average / 2).toFloat())

        // 导演
        helper!!.setText(R.id.tv_director, "导演：" + item!!.directors!![0].name)

        // 主演
        helper!!.setText(R.id.tv_leading_role, "主演：" + getActor(item.casts))

        // 看过
        helper!!.setText(R.id.tv_has_see, formatLargeNum(item!!.collect_count) + "人看过")

    }

    /**
     * 获取主演
     */
    private fun getActor(acList: List<HotShowBean.SubjectsBean.Cast>?): String {
        return if (acList != null && acList.isNotEmpty()) {
            var strBuilder = StringBuilder()
            acList.forEach {
                strBuilder.append(it.name + " / ")
            }
            strBuilder.delete(strBuilder.length - 2, strBuilder.length)
            strBuilder.toString()
        } else {
            ""
        }

    }

    fun formatLargeNum(number: Int): String {
        var ns = number.toString()
        if (ns.length < 5) {
            return ns
        }
        val a = (number / 10000f).toDouble()
        val df = DecimalFormat(".0")
        return df.format(a) + "万"
    }


}