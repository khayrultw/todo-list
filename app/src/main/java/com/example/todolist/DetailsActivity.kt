package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title") ?: "Title"
        val date = intent.getStringExtra("date") ?: "Date"
        val priority = intent.getShortExtra("priority", 0)
        val details = intent.getStringExtra("details") ?: "Details"

        binding.run {
            tvTitle.text = title
            tvDate.text = "Date: $date"
            tvPriority.text = "Priority: $priority"
            tvDetails.text = "Details: $details"
        }
    }
}