package com.nenartovich.p0861_asynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.tvInfo)
    }

    fun onclick(v: View) {
        val myTask = MyTask()
        myTask.execute()
    }

    inner class MyTask : AsyncTask<Unit, Unit, Unit>() {
        override fun onPreExecute() {
            super.onPreExecute()
            textView.text = "Begin"
        }

        override fun doInBackground(vararg p0: Unit?) {
            TimeUnit.SECONDS.sleep(1)
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            textView.text = "End"
        }
    }
}