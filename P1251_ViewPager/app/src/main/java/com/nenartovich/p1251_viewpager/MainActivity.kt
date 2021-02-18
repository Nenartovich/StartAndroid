package com.nenartovich.p1251_viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

const val TAG = "myLogs"
const val PAGE_COUNT = 10

class MainActivity : FragmentActivity() {

    private lateinit var pager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pager = findViewById(R.id.pager)
        pagerAdapter = MyFragmentPagerAdapter(supportFragmentManager)
        pager.adapter = pagerAdapter
        pager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                Log.d(TAG, "onPageSelected: ")
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }

    inner class MyFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount() = PAGE_COUNT

        override fun getItem(position: Int): Fragment = PageFragment.newInstance(position)

        override fun getPageTitle(position: Int): CharSequence? {
            return "Tab #$position"
        }
    }

}