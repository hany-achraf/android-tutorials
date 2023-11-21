package com.example.tutorials

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private val TAG = "Coroutines"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        GlobalScope.launch(Dispatchers.IO) {
//            val time = measureTimeMillis {
//                var res1 = doNetworkCall1()
//                var res2 = doNetworkCall2()
//                Log.d(TAG, res1)
//                Log.d(TAG, res2)
//            }
//            Log.d(TAG, "This code took $time ms.")
//        }

//        GlobalScope.launch(Dispatchers.IO) {
//            val time = measureTimeMillis {
//                var res1 = doNetworkCall1()
//                Log.d(TAG, res1)
//                var res2 = doNetworkCall2()
//                Log.d(TAG, res2)
//            }
//            Log.d(TAG, "This code took $time ms.")
//        }

//        GlobalScope.launch(Dispatchers.IO) {
//            val time = measureTimeMillis {
//                var res1: String? = null
//                var res2: String? = null
//
//                val job1 = launch { res1 = doNetworkCall1() }
//                val job2 = launch { res2 = doNetworkCall2() }
//
//                job1.join()
//                job2.join()
//
//                Log.d(TAG, res1.toString())
//                Log.d(TAG, res2.toString())
//            }
//            Log.d(TAG, "This code took $time ms.")
//        }

        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val res1 = async { doNetworkCall1() }
                val res2 = async { doNetworkCall2() }
                Log.d(TAG, res1.await())
                Log.d(TAG, res2.await())
            }
            Log.d(TAG, "This code took $time ms.")
        }
    }

    private suspend fun doNetworkCall1(): String {
        delay(3000L)
        return "OK #1"
    }

    private suspend fun doNetworkCall2(): String {
        delay(3000L)
        return "OK #2"
    }
}