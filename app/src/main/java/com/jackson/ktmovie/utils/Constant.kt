package com.jackson.ktmovie.utils


/*
* Constant  2016-11-14
* Copyright (c) 2016 HYB Co.Ltd. All right reserved.
*/
/*
* 保存全工程的常量
* @author Jackson
* @version 1.0.0
* since 2016-11-14
*/
object Constant {
    var LOG_DEBUG = true //控制工程的log信息打印

    val baseUrl = "https://api.douban.com/"
    val baseUrlJD = "https://yaoser.jd.com"

    val PAGE_COUNT = 5


    val typeBanner = 1         //轮播图
    val typeGvidMenu = 2      //功能菜单
    val typeMarquee = 3       //跑马灯
    val typeHot = 4           //七月爆品
    val typePrepare = 5      // 常备好药
    val typeChoice = 6        // 超值精选
    val typeFix = 7           //固定布局
    val typeScrollFix = 8    //可选固定
    val typeFloat = 9         //Float布局
    val typeColumn = 10         //栏格布局
    val typeOnePlusN = 11         //栏格布局
    val typeSticky = 12         //吸边布局
    val typeStaggered = 13         //瀑布流布局
}