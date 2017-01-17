package com.zqg.kotlin

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Button

/**
 * Created by zhuqiguang on 17/1/17.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnShow = findViewById(R.id.btn_show) as Button
        btnShow.setOnClickListener {
            val dialog = LoadingDialog(this, "加载中")
            dialog.show()
            Handler().postDelayed({ dialog.close() }, 5000)
        }
    }
}
