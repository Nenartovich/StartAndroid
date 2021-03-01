package com.nenartovich.p0931_servicestop

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MyService : Service() {
    val TAG = "myLogs"
    lateinit var es: ExecutorService
    lateinit var someRes: Any
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreateService: ")
        es = Executors.newFixedThreadPool(3)
        someRes = Any()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroyService: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommandService: ")
        val time = intent!!.extras!!.getInt("time", 1)
        val mr = MyRun(time, startId)
        es.execute(mr)
        return super.onStartCommand(intent, flags, startId)
    }
    
    override fun onBind(p0: Intent?): IBinder? {
        Log.d(TAG, "onBindService: ")
        return null
    }

    inner class MyRun(val time: Int, val startId: Int) : Runnable {
        init {
            Log.d(TAG, "MyRun#$startId create ")
        }

        override fun run() {
            Log.d(TAG, "MyRun#$startId start, time = $time")
            TimeUnit.SECONDS.sleep(time.toLong())

            try {
                Log.d(TAG, "MyRun#$startId  someRes = ${someRes.javaClass}")
            } catch (e: NullPointerException) {
                Log.d(TAG, "MyRun#$startId error, null pointer")
            }
            stop()
        }

        fun stop() {
            Log.d(TAG, "MyRun#" + startId + " end, stopSelfResult("
                    + startId + ") = " + stopSelfResult(startId))
        }
    }
}