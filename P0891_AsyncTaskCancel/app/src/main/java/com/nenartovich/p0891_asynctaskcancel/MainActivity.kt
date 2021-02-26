package com.nenartovich.p0891_asynctaskcancel

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val LOG_TAG = "myLogs"
    var myTask: MyTask? = null
    lateinit var tvInfo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInfo = findViewById(R.id.tvInfo)
    }

    fun onclick(v: View) {
        when (v.id) {
            R.id.btnStart -> {
                myTask = MyTask()
                myTask!!.execute()
            }
            R.id.btnCancel -> {
                cancelTask()
            }
        }
    }


    private fun cancelTask() {
        if (myTask == null) return
        Log.d(LOG_TAG, "Cancel result: ${myTask!!.cancel(false)}")
    }

    inner class MyTask : AsyncTask<Unit, Unit, Unit>() {

        override fun onPreExecute() {
            super.onPreExecute()
            tvInfo.text = "Begin";
            Log.d(LOG_TAG, "Begin");
        }

        override fun doInBackground(vararg p0: Unit?) {
            try {
                for (i in 0 until 5) {
                TimeUnit.SECONDS.sleep(1)
                if (isCancelled) return
                Log.d(LOG_TAG, "isCancelled: " + isCancelled());
            }
            } catch (e: InterruptedException) {
                Log.d(LOG_TAG, "Interrupted");
                e.printStackTrace();
            }
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            tvInfo.text = "End"
            Log.d(LOG_TAG, "End")
        }

        override fun onCancelled() {
            super.onCancelled()
            tvInfo.text = "Cancel"
            Log.d(LOG_TAG, "Cancel")
        }
    }
}