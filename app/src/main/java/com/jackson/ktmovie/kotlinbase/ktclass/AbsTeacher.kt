package com.jackson.ktmovie.kotlinbase.ktclass


/*
* AbsTeacher  2019-06-13
* Copyright (c) 2019 KL Co.Ltd. All right reserved.
*
*/
/*
* class description here
* @author Jackson
* @version 1.0.0
* since 2019 06 13
*/
// 可以用abstract声明一个抽象类
abstract class AbsTeacher: Teacher() {

    // 抽象成员在本类中可以不用实现
    override abstract  fun goToSchool()
}