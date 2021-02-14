package com.nenartovich.p0801_handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var tvInfo: TextView
    private lateinit var handler: Handler
    private lateinit var bStart: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInfo = findViewById(R.id.tvInfo)
        bStart = findViewById(R.id.bStart)
        handler = object : Handler() {
            override fun handleMessage(msg: android.os.Message) {
                tvInfo.text = "${msg.what} file(s) downloaded"
                if (msg.what == 10) bStart.isEnabled = true
                Toast.makeText(this@MainActivity, "${msg.what} file(s) downloaded", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.bStart -> {
                bStart.isEnabled = false
                val thread = Thread {
                    for (i in 1..10) {
                        downloadFile()
                        handler.sendEmptyMessage(i)
                        Log.d("TAG", "run: ")
                    }
                }
                thread.start()
            }
            R.id.bTest -> {
                Toast.makeText(this, "TEST!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun downloadFile() {
        TimeUnit.SECONDS.sleep(1)
    }
}