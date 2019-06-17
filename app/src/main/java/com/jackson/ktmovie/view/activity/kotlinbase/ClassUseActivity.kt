package com.jackson.ktmovie.view.activity.kotlinbase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jackson.ktmovie.R
import com.jackson.ktmovie.kotlinbase.ktclass.Student
import com.jackson.ktmovie.kotlinbase.ktclass.SuperStudent
import com.jackson.ktmovie.kotlinbase.ktclass.school


class ClassUseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_use)

        val student = Student("jackson", "22")
        println(student.myName + student.age)





        // 扩展函数
        fun SuperStudent.getScore(score: Int) {
            println("score==$score")
        }

        val superStudent = SuperStudent("jackson", "18", 100)
        superStudent.getScore(superStudent.prop)
        // 打印扩展属性
        println(superStudent.school)


    }
}
