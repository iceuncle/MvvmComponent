package com.example.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wanandroid.ui.demo.DemoFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, DemoFragment.newInstance())
                    .commitNow()
        }
    }
}