package com.example.tutorials

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var btnStartService: Button
    lateinit var btnStopService: Button
    lateinit var tvServiceStatus: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvServiceStatus = findViewById(R.id.tvServiceStatus)

        btnStartService = findViewById(R.id.btnStartService)
        btnStartService.setOnClickListener {
            Intent(this, MyIntentService::class.java).also {
                startService(it)
                tvServiceStatus.text = "Service running..."
            }
        }

        btnStopService = findViewById(R.id.btnStopService)
        btnStopService.setOnClickListener {
            MyIntentService.stopService()
            tvServiceStatus.text = "Service stopped..."
        }
    }
}