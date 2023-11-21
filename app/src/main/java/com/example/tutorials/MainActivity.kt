package com.example.tutorials

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorials.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val checkedMeat = findViewById<RadioButton>(binding.rgMeat.checkedRadioButtonId).text.toString()
            binding.tvOrder.text = "You ordered burger with: \n$checkedMeat" +
                    "${if (binding.cbCheese.isChecked) "\nCheese" else ""}" +
                    "${if (binding.cbOnion.isChecked) "\nOnion" else ""}" +
                    "${if (binding.cbSalad.isChecked) "\nSalad" else ""}"
        }
    }
}