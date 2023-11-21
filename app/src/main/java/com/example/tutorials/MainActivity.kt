package com.example.tutorials

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorials.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "Coroutines"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Starting coroutine in thread ${Thread.currentThread().name}")
            val res = doNetworkCall()
            withContext(Dispatchers.Main) {
                Log.d(TAG, "Setting text in thread ${Thread.currentThread().name}")
                binding.tvDummy.text = res
            }
        }
    }

    suspend fun doNetworkCall(): String {
        delay(3000L)
        return "OK"
    }
}