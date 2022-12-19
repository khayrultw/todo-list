package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.databinding.ActivityAddEditTaskBinding
import com.google.android.material.snackbar.Snackbar

class AddTaskActivity : BaseAddEditTaskActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnSave.setOnClickListener {
            val intent = getResultWithIntent()
            intent?.let {
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}

// var args
// Droid4X