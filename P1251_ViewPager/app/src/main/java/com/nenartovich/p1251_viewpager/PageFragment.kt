package com.nenartovich.p1251_viewpager

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.Random

const val ARGUMENT_PAGE_NUMBER = "arg_page_number"
class PageFragment : Fragment() {
    private var pageNumber: Int = 0
    private var backColor: Int = 0

    companion object {
        fun newInstance(page: Int): PageFragment {
            val pageFragment = PageFragment()
            val args = Bundle()
            args.putInt(ARGUMENT_PAGE_NUMBER, page)
            pageFragment.arguments = args
            return pageFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = arguments!!.getInt(ARGUMENT_PAGE_NUMBER, 1)
        val rnd = Random()
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment, null)
        val tv = view.findViewById<TextView>(R.id.tvPage)
        tv.text = "Page " + pageNumber
        tv.setBackgroundColor(backColor)
        return view
    }

}