package com.jackson.ktmovie.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState)
    // setContentView(R.layout.activity_base)

    /**
     * 扩展Context，添加toast
     */
//    fun Context.toast(message:String, duration:Int= Toast.LENGTH_SHORT) =
//            Toast.makeText(this,message,duration).show()
}
