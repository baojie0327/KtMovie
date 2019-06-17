package com.jackson.ktmovie.kotlinbase.ktclass

/*
*   2019-05-24
* Copyright (c) 2019 KL Co.Ltd. All right reserved.
*
*/
/*
* class description here
* @author Jackson
* @version 1.0.0
* since 2019 05 24
*/

// 在 Kotlin 中的一个类可以有一个主构造函数以及一个或多个次构造函数
//  主构造函数是类头的一部分：它跟在类名（与可选的类型参数）后
// 如果主构造函数没有任何注解或者可见性修饰符，可以省略这个 constructor 关键字

// 一种简洁语法，可以通过主构造器来定义属性并初始化属性值（可以是var或val）
// 也就是说，用var或val，可以直接在对象中引用，否则要重新定义接收

open class Student(val name: String, var age: String) {

    // 主构造的参数可以在初始化块中使用。它们也可以在类体内声明的属性初始化器中使用
    var myName = name.toUpperCase()

    // 主构造函数不能包含任何的代码。初始化的代码可以放到以 init 关键字作为前缀的初始化块
    init {
         name.toUpperCase()

    }


    //初始化块中的代码实际上会成为主构造函数的一部分。
    // 委托给主构造函数会作为次构造函数的第一条语句，
    // 因此所有初始化块中的代码都会在次构造函数体之前执行


    // 类也可以声明前缀有 constructor的次构造函数
    // 如果类有一个主构造函数，每个次构造函数需要委托给主构造函数，
    // 可以直接委托或者通过别的次构造函数间接委托。委托到同一个类的另一个构造函数用 this 关键字即可
    constructor(name: String, age: String, score: String) : this(name, age)

    // 使用fun声明函数时，此函数默认为final修饰，不能被子类重写
    // 如果允许子类重写该函数，那么就要手动添加 open 修饰它, 子类重写方法使用 override 关键词
    open fun goToSchool(){
         println("去学校上学")
     }

    fun study(){
        println("上课")
    }



}