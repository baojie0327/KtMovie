package com.jackson.ktmovie.bean

/**
 * HomeBean  2018-08-06
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 08 06
 */
class HomeBean : BaseBean<HomeBean.DataBean>() {

    class DataBean: BasicBean() {
        val id: String? = null
        val name: String? = null
        val type: String? = null
        val items: List<ItemsBean>? = null

        class ItemsBean: BasicBean() {
            val bigImg: String? = null
            val img: String? = null
            val url: String? = null
            val title: String? = null
            val sort: String? = null
            val id: String? = null
            val name: String? = null
            val type: String? = null
            val item: ItemBean? = null

            class ItemBean: BasicBean() {
                val img: String? = null
                val type: String? = null
                val content: String? = null
                val skuId: String? = null
                val shopId: String? = null
                val factoryName: String? = null
                val mainImg: String? = null
                val shopName: String? = null
                val skuName: String? = null
            }
        }


    }
}