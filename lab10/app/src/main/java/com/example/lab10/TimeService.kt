package com.example.lab10

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class TimeService : Service() {

    private var initialCount = 0
    private var counter = initialCount
    private lateinit var job: Job
    private val myBinder = MyBinder()
    private var interval = 1

    override fun onBind(intent: Intent?): IBinder? {
        return myBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            initialCount = intent.getIntExtra("currentInitialCount", 0)
            interval = intent.getIntExtra("updateInterval", 1)
        }
        counter = initialCount
        job = GlobalScope.launch {
            while (true) {
                delay((1000 * interval).toLong())
                Log.d("SERVICE", "Timer Is Ticking: " + counter)
                counter++
                val intent = Intent(BROADCAST_TIME_EVENT)
                intent.putExtra("counter", counter)
                sendBroadcast(intent)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("SERVICE", "onDestroy")
        job.cancel()
        super.onDestroy()
    }

    inner class MyBinder : Binder() {
        fun getService(): TimeService {
            return this@TimeService
        }
    }

    fun getCounter(): Int = counter

    fun setInitialCount(newInitialCount: Int) {
        initialCount = newInitialCount
    }

    fun setUpdateInterval(newUpdateInterval: Int) {
        interval = newUpdateInterval
    }
}