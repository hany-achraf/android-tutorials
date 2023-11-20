package com.example.tutorials

import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorials.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dragListener: View.OnDragListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dragView.setOnLongClickListener {
            val label = "This is clip text"
            val mimeTypes = arrayOf<String>(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val clipDescription = ClipDescription(label, mimeTypes)
            val clipData = ClipData(clipDescription, ClipData.Item(label))

            val shadowBuilder = View.DragShadowBuilder(it)

            it.startDragAndDrop(clipData, shadowBuilder, it, 0)
            it.visibility = View.INVISIBLE
            true
        }

        dragListener = View.OnDragListener { view, event ->
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED ->
                    event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
                DragEvent.ACTION_DRAG_ENTERED -> {
                    view.invalidate()
                    true
                }
                DragEvent.ACTION_DRAG_LOCATION -> true
                DragEvent.ACTION_DRAG_EXITED -> {
                    view.invalidate()
                    true
                }
                DragEvent.ACTION_DROP -> {
                    val item = event.clipData.getItemAt(0)
                    val data = item.text
                    Toast.makeText(this, data, Toast.LENGTH_SHORT).show()

                    val v = event.localState as View
                    val owner = v.parent as ViewGroup
                    owner.removeView(v)

                    val destination = view as LinearLayout
                    destination.addView(v)

                    v.visibility = View.VISIBLE
                    true
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    view.invalidate()
                    true
                }
                else -> false
            }
        }

        binding.llTop.setOnDragListener(dragListener)
        binding.llBottom.setOnDragListener(dragListener)
    }
}