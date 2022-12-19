package com.example.todolist.models

data class Task(
    val id: Long? = null,
    val title: String,
    val date: String,
    val details: String,
    val priority: Short,
    val color: NoteColor
)
//) {
//    fun getTaskWithId(_id: Long): Task {
//        return Task(
//            _id,
//            title,
//            date,
//            details,
//            priority,
//
//        )
//    }
//}
