package com.example.tutorials

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var btnStartService: Button
    lateinit var btnStopService: Button
    lateinit var tvServiceStatus: TextView

    lateinit var etDataString: EditText
    lateinit var btnSendData: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvServiceStatus = findViewById(R.id.tvServiceStatus)

        btnStartService = findViewById(R.id.btnStartService)
        btnStartService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                startService(it)
                tvServiceStatus.text = "Service is running..."
            }
        }

        btnStopService = findViewById(R.id.btnStopService)
        btnStopService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                stopService(it)
                tvServiceStatus.text = "Service is stopped..."
            }
        }

        btnSendData = findViewById(R.id.btnSendData)
        btnSendData.setOnClickListener {
            Intent(this, MyService::class.java).also {
                etDataString = findViewById(R.id.etDataString)
                it.putExtra("EXTRA_DATA", etDataString.text.toString())
                startService(it)
            }
        }
    }
}