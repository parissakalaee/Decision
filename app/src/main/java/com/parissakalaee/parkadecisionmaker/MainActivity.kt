package com.parissakalaee.parkadecisionmaker

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<View>(R.id.pagerMain) as ViewPager
        val myAdapter = PagerAdapter(supportFragmentManager)
        viewPager.adapter = myAdapter
        viewPager.currentItem = 1
    }
}