package com.example.todolist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.ActivityAddEditTaskBinding
import com.google.android.material.snackbar.Snackbar

open class BaseAddEditTaskActivity: AppCompatActivity() {
    protected lateinit var binding: ActivityAddEditTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    protected fun getResultWithIntent(): Intent? {
        val intent = Intent()
        val title = binding.etTitle.text.toString()
        val date = binding.etDate.text.toString()
        val priority = binding.etPriority.text.toString()
        val details = binding.etDetails.text.toString()

        if (validate(title, date, priority, details)) {
            intent.putExtra("title", title)
            intent.putExtra("date", date)
            intent.putExtra("priority", priority.toShort())
            intent.putExtra("details", details)
        } else {
            val snackBar =
                Snackbar.make(binding.root, "Please enter all the values", Snackbar.LENGTH_LONG)
            snackBar.show()
            return null
        }

        return intent
    }

    private fun validate(vararg list: String): Boolean {
        for(field in list) {
            if(field.isEmpty()) {
                return false
            }
        }
        return true
    }
}