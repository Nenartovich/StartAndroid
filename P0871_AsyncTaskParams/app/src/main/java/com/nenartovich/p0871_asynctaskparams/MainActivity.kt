package com.nenartovich.p0871_asynctaskparams

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
        val mt = MyTask();
        mt.execute("file_path_1", "file_path_2", "file_path_3", "file_path_4");
    }

    inner class MyTask : AsyncTask<String, Int, Unit>() {

        override fun onPreExecute() {
            super.onPreExecute()
            textView.text = "Begin"
        }

        override fun doInBackground(vararg p0: String) {
            var cnt = 0
            for (url in p0) {
                downloadFile(url)
                publishProgress(++cnt)
            }

            TimeUnit.SECONDS.sleep(1)
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            textView.text = "Downloaded ${values[0]} files"
        }
        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            textView.text = "End"
        }

        private fun downloadFile(s: String) {
            TimeUnit.SECONDS.sleep(2)
        }
    }
}
