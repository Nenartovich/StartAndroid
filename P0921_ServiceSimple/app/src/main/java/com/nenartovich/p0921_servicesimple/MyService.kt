package com.nenartovich.p0921_servicesimple

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.concurrent.TimeUnit

class MyService : Service() {

    val TAG = "myLogs"

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreateService: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroyService: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommandService: ")
        someTask()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder?  {
        Log.d(TAG, "onBindService: ")
        return null
    }

    fun someTask() {
        Thread {
            for (i in 1..5) {
                Log.d(TAG, "someTask: i = $i")
                TimeUnit.SECONDS.sleep(1)
            }
            stopSelf()
        }.start()
    }
}