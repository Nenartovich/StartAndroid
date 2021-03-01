package com.nenartovich.p0921_servicesimple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {

    val TAG = "myLogs"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreateActivity: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickStart(v: View) {
        startService(Intent(this, MyService::class.java))
    }

    fun onClickStop(v: View) {
        stopService(Intent(this, MyService::class.java))
    }

    override fun onStart() {
        Log.d(TAG, "onStartActivity: ")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResumeActivity: ")
        super.onResume()
    }

    override fun onRestart() {
        Log.d(TAG, "onRestartActivity: ")
        super.onRestart()
    }

    override fun onPause() {
        Log.d(TAG, "onPauseActivity: ")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStopActivity: ")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroyActivity: ")
        super.onDestroy()
    }
}