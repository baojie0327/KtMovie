import com.jackson.ktmovie.kotlinbase.ktclass.Teacher

/*
* SuperTeacher  2019-06-12
* Copyright (c) 2019 KL Co.Ltd. All right reserved.
*
*/
/*
* class description here
* @author Jackson
* @version 1.0.0
* since 2019 06 12
*/

// 如果派生类没有主构造函数，那么必须在每一个次构造函数必须使用 super 关键字初始化其基类型
class SuperTeacher : Teacher {

    constructor(name: String) : super(name)

    constructor(name: String, age: Int) : super(name,age)
}