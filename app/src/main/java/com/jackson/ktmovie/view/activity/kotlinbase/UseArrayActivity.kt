package com.jackson.ktmovie.view.activity.kotlinbase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jackson.ktmovie.R

class UseArrayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_array)
    }


    /**
     * 数组
     */
    fun arrayTest(){
        // 创建数组
        var arr1= arrayOf("java","kotlin","flutter")
        // 创建制定长度，元素为null的数组
        var arr2= arrayOfNulls<String>(5)
        // 创建长度为0的空数组
        var arr3= emptyArray<String>()
        // 使用构造器
        var arr4=Array(5,{it})
    }

}
