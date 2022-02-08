package com.parissakalaee.parkadecisionmaker

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.viewpager.widget.ViewPager

class MainActivity : ActivityEnhancedActionBar() {
    var back_pressed: Long = 0
    override fun onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            G.currentActivity!!.finish()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            G.currentActivity!!.startActivity(intent)
            System.exit(0)
        } else Toast.makeText(
            G.context,
            "جهت خروج کلید بازگشت را مجدداً بفشارید",
            Toast.LENGTH_LONG
        ).show()
        back_pressed = System.currentTimeMillis()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialization
        val viewPager = findViewById<View>(R.id.pager) as ViewPager
        val myAdapter = PagerAdapter(supportFragmentManager)
        //		PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
//		myPagerTabStrip.setTabIndicatorColor(Color.parseColor("#00FFFF"));
        val Inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        ActionBar.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        val view = Inflater.inflate(R.layout.custom_action_bar, null)
        val actionBar = supportActionBar
        actionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar.setCustomView(
            view,
            ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#00FFFF")))
        viewPager.adapter = myAdapter
        viewPager.currentItem = 1
        val imgClose = findViewById<View>(R.id.imgClose) as ImageView
        imgClose.setOnClickListener { view -> onCreatePopupMenu(view) }
    }

    override fun onMenuItemClick(arg0: MenuItem): Boolean {
        // TODO Auto-generated method stub
        return false
    }
}