package com.example.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.broadcasttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var timeChangeReceiver: TimeChangeReceiver
    lateinit var binding: ActivityMainBinding
    lateinit var myBroadcastReceiver: MyBroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        //IntentFilter 是 Android 中用于定义广播接收器（BroadcastReceiver）所感兴趣的广播类型的一个对象。
        // 它用于匹配和过滤系统或应用广播的 Intent，只有当广播的 Intent 与 IntentFilter 中定义的条件匹配时，广播接收器才会被触发。
        //IntentFilter()：创建一个空的 IntentFilter，可以后续使用 addAction()、addCategory() 等方法来添加过滤条件。
        //IntentFilter(String action)：创建一个 IntentFilter，并立即指定一个 action。
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)

        binding.button.setOnClickListener{
            val intent =Intent("com.example.broadcasttest.My_BROADCAST")
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
        unregisterReceiver(myBroadcastReceiver)
    }

    inner class TimeChangeReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            Toast.makeText(context, "Time has changed", Toast.LENGTH_SHORT).show()
        }

    }
}