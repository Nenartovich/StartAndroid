package com.nenartovich.p0911_asynctaskrotate

import android.annotation.SuppressLint
import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.util.concurrent.TimeUnit

class MainActivity : Activity() {

    private var myTask: MyTask? = null
    lateinit var tv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myTask = lastNonConfigurationInstance as MyTask?
        if (myTask == null) {
            myTask = MyTask()
            myTask!!.execute()
        }
        myTask!!.link(this)
        tv = findViewById(R.id.tv)

    }

    override fun onRetainNonConfigurationInstance(): Any? {
        myTask!!.unLink()
        return myTask
    }

    companion object {
        class MyTask : AsyncTask<String, Int, Unit>() {
            @SuppressLint("StaticFieldLeak")
            private var mainActivity: MainActivity? = null

            fun link(act: MainActivity) {
                mainActivity = act
            }

            fun unLink() {
                mainActivity = null
            }

            override fun doInBackground(vararg p0: String?) {
                try {
                    for (i in 1..10) {
                        TimeUnit.SECONDS.sleep(1)
                        publishProgress(i)
                        Log.d("qwe", "i = $i, MyTask: ${this.hashCode()}, MainActivity: + ${mainActivity.hashCode()}")
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onProgressUpdate(vararg values: Int?) {
                super.onProgressUpdate(*values)
                mainActivity!!.tv.text = "i = ${values[0]}"
            }
        }
    }
}