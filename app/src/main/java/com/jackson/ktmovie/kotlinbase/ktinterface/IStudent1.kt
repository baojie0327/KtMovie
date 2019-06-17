package com.jackson.ktmovie.kotlinbase.ktinterface

interface IStudent1 {


    // 接口中的方法
    // 如果没有实现方法体，实现接口时必须重写
    // 如果有实现方法体，实现时不必重写

    fun breakfast()  // 如果没有实现方法体，实现接口时必须重写

    fun lanch(){    // 如果有实现方法体，实现时不必重写
        println("吃午饭")
    }

    fun goToSchool(){     // 接口成员默认就是“open”的
        // 可选方法体
        println("去学校教学")
    }
}