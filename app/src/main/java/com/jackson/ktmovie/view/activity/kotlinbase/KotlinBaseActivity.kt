package com.jackson.ktmovie.view.activity.kotlinbase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jackson.ktmovie.R
import kotlinx.android.synthetic.main.activity_kotlin_base.*
import org.jetbrains.anko.startActivity


// 定义变量
//

class KotlinBaseActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_base)

        base_use_one.setOnClickListener(this)
        base_use_class.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.base_use_one -> startActivity<BaseOneActivity>()
                R.id.base_use_class->startActivity<ClassUseActivity>()
            }
        }
    }
}
