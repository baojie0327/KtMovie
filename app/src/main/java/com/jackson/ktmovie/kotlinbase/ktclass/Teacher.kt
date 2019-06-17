package com.jackson.ktmovie.kotlinbase.ktclass

/*
* Teacher  2019-06-12
* Copyright (c) 2019 KL Co.Ltd. All right reserved.
*
*/
/*
* class description here
* @author Jackson
* @version 1.0.0
* since 2019 06 12
*/
open class Teacher {

    constructor()
    constructor(name:String)
    constructor(name:String,age:Int)

    open fun goToSchool(){
        println("去学校教学")
    }

    fun teach(){
        println("教学")
    }

}