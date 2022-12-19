package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.adapters.TaskAdapter
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.models.Task
import com.example.todolist.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: TaskAdapter
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)

        setupNavigation()
        initAdapter()

        viewModel.tasks.observe(this) { tasks ->
            binding.tvEmpty.visibility =
                if(tasks.isEmpty()) View.VISIBLE else View.GONE
            adapter.setTasks(tasks)
        }

    }

    private fun setupNavigation() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if(result.resultCode == RESULT_OK) {
                result.data?.let {
                    val id = it.getLongExtra("id", -1L)
                    val title = it.getStringExtra("title") ?: "Title"
                    val date = it.getStringExtra("date") ?: "Date"
                    val priority = it.getShortExtra("priority", 0)
                    val details = it.getStringExtra("details") ?: "Details"

                    val task = Task(id, title, date, details, priority)

                    if(id == -1L) {
                        viewModel.addNewTask(task)
                    } else {
                        viewModel.editTask(task)
                    }


                    Log.d("debugging", task.toString())
                }
            }
        }

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            resultLauncher.launch(intent)
        }
    }


    private fun initAdapter() {
        adapter = TaskAdapter(
            emptyList(),
            {
                viewModel.deleteTask(it)
            },
            {
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("title", it.title)
                intent.putExtra("date", it.date)
                intent.putExtra("priority", it.priority)
                intent.putExtra("details", it.details)
                startActivity(intent)

            },
            {
                val intent = Intent(this, EditTaskActivity::class.java)
                intent.putExtra("id", it.id)
                intent.putExtra("title", it.title)
                intent.putExtra("date", it.date)
                intent.putExtra("priority", it.priority)
                intent.putExtra("details", it.details)
                resultLauncher.launch(intent)
            }
        )
        val layoutManager = GridLayoutManager(this, 2)
        binding.rvTasks.layoutManager = layoutManager
        binding.rvTasks.adapter = adapter
    }
}
// Edit and Delete