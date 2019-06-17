package com.jackson.ktmovie.kotlinbase.ktclass1


/*
* User  2019-06-14
* Copyright (c) 2019 KL Co.Ltd. All right reserved.
*
*/
/*
* class description here
* @author Jackson
* @version 1.0.0
* since 2019 06 14
*/
/**
 * 数据类,只保存数据的类
 */

// 主构造函数需要至少有一个参数
// 主构造函数的所有参数需要标记为 val 或 var
// 数据类不能是抽象、开放、密封或者内部的
// 在1.1之前）数据类只能实现接口
data class User(val name:String, var age:Int) {
}