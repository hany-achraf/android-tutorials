package com.example.tutorials

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isEnabled = intent?.getBooleanExtra("state", false) ?:return
        if (isEnabled) {
            Toast.makeText(context, "Airplane mode is enabled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Airplane mode is disabled", Toast.LENGTH_LONG).show()
        }
    }
}