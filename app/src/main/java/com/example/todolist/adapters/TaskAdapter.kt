package com.example.todolist.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemLayoutTaskBinding
import com.example.todolist.models.Task

class TaskAdapter(
    private var items: List<Task>,
    val onDelete: (id: Long) -> Unit,
    val onClick: (task: Task) -> Unit,
    val onEdit: (task: Task) -> Unit
): RecyclerView.Adapter<TaskAdapter.ItemTaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTaskHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutTaskBinding.inflate(layoutInflater, parent, false)
        return ItemTaskHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemTaskHolder, position: Int) {
       val item = items[position]
        holder.binding.run {
            tvTitle.text = item.title
            tvDate.text = item.date
            cvTaskItem.setOnClickListener {
                onClick(item)
            }

            tvDelete.setOnClickListener {
                onDelete(item.id!!) // Not null assertion
            }

            tvEdit.setOnClickListener {
                onEdit(item)
            }

            item.color.value
        }

        //Log.d("debugging", "inside onBindViewHolder $position")
    }

    override fun getItemCount() = items.size

    fun setTasks(items: List<Task>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ItemTaskHolder(val binding: ItemLayoutTaskBinding): RecyclerView.ViewHolder(binding.root)
}