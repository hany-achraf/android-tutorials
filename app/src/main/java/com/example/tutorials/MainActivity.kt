package com.example.tutorials

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

class MainActivity : AppCompatActivity() {
    private val TAG = "Coroutines"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val job = GlobalScope.launch(Dispatchers.Default) {
            withTimeout(10L) {
                for (i in 30..40) {
                    if (isActive) {
                        Log.d(TAG, "Result for i = $i: ${fib(i)}")
                    }
                }
            }
//            for (i in 30..40) {
//                if (isActive) {
//                    Log.d(TAG, "Result for i = $i: ${fib(i)}")
//                }
//            }
            Log.d(TAG, "End long running calculations")
        }

//        runBlocking {
//            delay(1000L)
//            job.cancel()
//            Log.d(TAG, "Main Thread is continuing...")
//        }
    }

//    private fun fib(i: Int): Int {
//        if (i < 2) return 1
//        return fib(i - 1) + fib(i - 2)
//    }

    /** Solution with memoization */
    private var map = HashMap<Int, Int>()
    private fun fib(i: Int): Int {
        if (map.containsKey(i)) return map[i] ?: 0
        if (i < 2) return 1
        map[i] = fib(i - 1) + fib(i - 2)
        return map[i] ?: 0
    }
}