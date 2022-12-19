package com.example.todolist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.ActivityAddEditTaskBinding
import com.google.android.material.snackbar.Snackbar

class EditTaskActivity: BaseAddEditTaskActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getLongExtra("id", -1)

        binding.run {
            btnSave.text = "Update"
            etTitle.setText(intent.getStringExtra("title"))
            etDate.setText(intent.getStringExtra("date"))
            etDetails.setText(intent.getStringExtra("detail"))
            etPriority.setText(intent.getShortExtra("priority", -1).toString())

            btnSave.setOnClickListener {
                val intent = getResultWithIntent()
                intent?.let {
                    intent.putExtra("id", id)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }
    }
}

// add edit delete
// recyclerview with viewpager
// custom view
// appbar layout
// spinner with autocomplete edittext