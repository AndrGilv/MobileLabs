package com.example.lab10

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.TextView

const val BROADCAST_TIME_EVENT = "com.example.lab10.time_event"

class MainActivity : AppCompatActivity() {

    var myService: TimeService? = null
    var isBound = false
    var receiver: BroadcastReceiver? = null
    private var updateInterval = 1
    private var currentInitialCount = 0

    val myConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as TimeService.MyBinder
            myService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val counter = intent.getIntExtra("counter", 0)
                val textCounter = findViewById<TextView>(R.id.counterTextView)
                textCounter.text = counter.toString()
            }
        }
        val filter = IntentFilter(BROADCAST_TIME_EVENT)
        registerReceiver(receiver, filter)

        val intervalUpdateSeekBar: SeekBar = findViewById(R.id.intervalUpdateSeekBar)
        intervalUpdateSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateInterval = progress
                Log.e("app_info", "updateInterval = $updateInterval")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (isBound)
                    myService!!.setUpdateInterval(updateInterval)
            }

        })

        val initialCountSeekBar: SeekBar = findViewById(R.id.initialCountSeekBar)
        initialCountSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{


            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                currentInitialCount = progress
                Log.e("app_info", "currentInitialCount = $currentInitialCount")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (isBound)
                    myService!!.setInitialCount(currentInitialCount)
            }

        })
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

    fun buttonStartService(view: View) {
        val intent = Intent(this, TimeService::class.java)
        intent.putExtra("updateInterval", updateInterval)
        intent.putExtra("currentInitialCount", currentInitialCount)
        startService(intent)
        bindService(Intent(this, TimeService::class.java), myConnection, Context.BIND_AUTO_CREATE)
    }

    fun buttonStopService(view: View) {
        stopService(Intent(this, TimeService::class.java))
        unbindService(myConnection)
    }

    fun buttonGetValue(view: View) {
        if (isBound)
            findViewById<TextView>(R.id.counterTextView).text = myService!!.getCounter().toString()
    }
}