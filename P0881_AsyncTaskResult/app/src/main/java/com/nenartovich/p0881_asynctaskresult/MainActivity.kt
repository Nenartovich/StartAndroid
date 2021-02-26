package com.nenartovich.p0881_asynctaskresult

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val LOG_TAG = "myLogs"
    lateinit var textView: TextView
    var myTask: MyTask? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.tvInfo)
    }

    fun onclick(v: View) {
        when (v.id) {
            R.id.btnStart -> {
                myTask = MyTask()
                myTask!!.execute()
            }
            R.id.btnGet -> {
                showResult()
            }
        }
    }

    private fun showResult() {
        if (myTask == null) return
        var result = -1
        Log.d(LOG_TAG, "Try to get result")
        result = myTask!!.get()!!
        Log.d(LOG_TAG, "get returns $result")
        Toast.makeText(this, "get returns $result", Toast.LENGTH_SHORT).show()
    }

    inner class MyTask : AsyncTask<Unit, Unit, Int?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            textView.text = "Begin"
            Log.d(LOG_TAG, "Begin")
        }

        override fun doInBackground(vararg p0: Unit?): Int {
            TimeUnit.SECONDS.sleep(3)
            return 100500
        }

        override fun onPostExecute(result: Int?) {
            super.onPostExecute(result)
            textView.text = "End"
            Log.d(LOG_TAG, "End")
        }
    }

}