import com.jackson.ktmovie.kotlinbase.ktclass.Student

/*
* SuperStudent1  2019-06-12
* Copyright (c) 2019 KL Co.Ltd. All right reserved.
*
*/
/*
* class description here
* @author Jackson
* @version 1.0.0
* since 2019 06 12
*/
class SuperStudent1: Student {
    constructor(name:String,age:String):super(name,age)
    constructor(name:String,age:String,score:String):super(name, age, score)
}