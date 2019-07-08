package com.jackson.ktmovie.view.activity.kotlinbase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jackson.ktmovie.R

class BaseUseFunctionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_use_function)

        System.out.println("x==" + doublex(3))
        System.out.println("y=" + func1(b = 4))     // 如果默认参数在无默认参数之前，只能通过命名参数的方式调用
        System.out.println("y=" + func1(3, 5))
    }


    //函数的定义
    fun doublex(x: Int): Int {
        return 2 * x
    }

    /**
     * 带默认参数的函数定义
     */
    fun func1(a: Int = 2, b: Int = 3): Int {
        return a + b
    }

    // 单表达式函数
    // 当函数返回单个表达式时，可以省略花括号并且在 = 符号之后指定代码体即可
    fun func2(x: Int): Int = x * 2
    fun func3(x: Int) = x * 2

    // 可变数量的参数
    fun <T> asList(vararg ts: T): List<T> {
        val result = ArrayList<T>()
        for (t in ts) // ts is an Array
            result.add(t)
        return result
    }


}
