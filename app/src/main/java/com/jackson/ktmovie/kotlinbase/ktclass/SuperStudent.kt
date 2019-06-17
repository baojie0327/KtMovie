package com.jackson.ktmovie.kotlinbase.ktclass

import com.jackson.ktmovie.kotlinbase.ktinterface.IStudent
import com.jackson.ktmovie.kotlinbase.ktinterface.IStudent1


// 继承
//子类有主构造函数
// 如果子类有主构造函数， 则基类必须在主构造函数中立即初始化

//  实现接口时，必须重写属性  prop
class SuperStudent(name: String, age: String, override val prop: Int) : Student(name, age), IStudent, IStudent1 {


    // 派生类中的代码可以使用 super 关键字调用其超类的函数与属性访问器的实现
    // 如果有多个相同的方法（继承或者实现自其他类，如A、B类），则必须要重写该方法，使用super范型去选择性地调用父类的实现
    override fun goToSchool() {
        super<Student>.goToSchool()
        super<IStudent>.goToSchool()
    }


    // 内部类
    inner class MiddleStudent {

        fun study() {
            this@SuperStudent.goToSchool()
        }
    }


    //  实现接口时，必须重写属性  prop
//    override val prop: Int
    //       get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun breakfast() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    // 实现多个接口时，可能会遇到同一方法继承多个实现的问题
    override fun lanch() {
        super<IStudent>.lanch()
        super<IStudent1>.lanch()
    }

}

// 扩展属性
// 扩展属性允许定义在类或者kotlin文件中，不允许定义在函数中
// 初始化属性因为属性没有后端字段（backing field），所以不允许被初始化，只能由显式提供的 getter/setter 定义
val SuperStudent.school: String
    get() = "College"