package com.example.todolist.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.models.Task

class MainViewModel: ViewModel() {
    private var counter = 0L
    private val tasksMap: MutableMap<Long, Task> = mutableMapOf(
    )
    val tasks: MutableLiveData<List<Task>> = MutableLiveData()

    fun addNewTask(task: Task) {
        counter++
        tasksMap[counter] = task.getTaskWithId(counter)

        tasks.value = tasksMap.values.toList()
    }

    fun deleteTask(id: Long) {
        tasksMap.remove(id)
        tasks.value = tasksMap.values.toList()
    }

    fun editTask(task: Task) {

    }
}

// FrameLayout

// O(log(n))