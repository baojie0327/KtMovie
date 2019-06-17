package com.jackson.ktmovie.view.activity.kotlinbase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jackson.ktmovie.R

class BaseOneActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_one)

        basicTest()
        stringTest()
        stringMode()
        flowcontrol()

    }

    /**
     * 基本类型的测试
     */
    private fun basicTest() {
        val oneMillion = 1_000_000
        val creditCardNum = 1234_4567_8798_8999L

        // Kotlin 中没有基础数据类型，只有封装的数字类型，你每定义的一个变量，其实 Kotlin 帮你封装了一个对象，这样可以保证不会出现空指针
        // Kotlin 中，三个等号 === 表示比较对象地址，两个 == 表示比较两个值大小

        val a = 1000

        //经过了装箱，创建了两个不同的对象
        val boxA: Int? = a
        val anotherBoxA: Int? = a
        // 比较数据的大小
        println(a == a)
        println(boxA == anotherBoxA) // true，值相等
        // 比较对象
        println(a === a)  // 值相等，对象地址相等
        println(boxA === anotherBoxA) //  false，值相等，对象地址不一样

    }


    /**
     * 字符串
     */
    private fun stringTest() {
        // 字符串用 String 类型表示。字符串是不可变的。
        // 字符串的元素——字符可以使用索引运算符访问: s[i]
        // 可以用 for 循环迭代字符串

        val str = "Jackson"
        println(str[1])
        for (c in str) {
            println(c)
        }
        // 可以用 + 操作符连接字符串。这也适用于连接字符串与其他类型的值， 只要表达式中的第一个元素是字符串
        val str1 = "abc" + 1
        println(str1 + "def")

        // 多行显示的字符
        // 原始字符串 使用三个引号（"""）分界符括起来，内部没有转义并且可以包含换行以及任何其他字符:
        // 默认 | 用作边界前缀，但你可以选择其他字符并作为参数传入，比如 trimMargin(">")。
        val text = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()

        println(text)
    }


    /**
     * 字符串模板
     */
    private fun stringMode() {
        // 字符串模板
        // 字符串可以包含模板表达式 ，即一些小段代码，会求值并把结果合并到字符串中。 模板表达式以美元符（$）开头
        val i = 10
        println("i=$i")

        // 或者用花括号括起来的任意表达式
        val s = "jackson"
        println("$s.length is ${s.length}")
        // 如果要用原始字符串 $ 字符
        println("${'$'} 9.99")

    }

    /**
     * 流程控制
     */
    private fun flowcontrol() {

        // kotlin中，if是一个表达式，它会返回一个值
        val a=8
        val b=10
        // 作为表达式
        val max = if (a > b) a else b

        // when表达式
        var x=2

        when(x){
            1-> println("x=21")
            2-> println("x==2")
            3,4-> println("x==3 or x==4")
            in 5..8-> println("x in [5,8]")
            else->{
                println("x!=1 && x!=2")
            }
        }

    }


    /**
     * 返回和跳转
     */
    private fun returnandbreak(){
        // Break 与 Continue 标签
        // 在 Kotlin 中任何表达式都可以用标签（label）来标记
        // 标签的格式为标识符后跟 @ 符号,例如：abc@、fooBar@都是有效的标签

        // 要为一个表达式加标签，我们只要在其前加标签即可
        loop@ for (i in 1..100) {
            // ……
        }

        loop@ for (i in 1..100) {
            for (j in 1..100) {
                if (j in 5..9) break@loop
            }
        }



    }



    // 标签处返回

    // 从最直接包围它的函数即 foo 中返回
    fun foo1() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return // 非局部直接返回到 foo() 的调用者
            print(it)
        }
        println("this point is unreachable")
    }


    fun foo2() {
        listOf(1, 2, 3, 4, 5).forEach lit@{
            if (it == 3) return@lit // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
            print(it)
        }
        print(" done with explicit label")
    }

    fun foo3() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@forEach // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
            print(it)
        }
        print(" done with implicit label")
    }

    fun foo4() {
        listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
            if (value == 3) return  // 局部返回到匿名函数的调用者，即 forEach 循环
            print(value)
        })
        print(" done with anonymous function")
    }





}
