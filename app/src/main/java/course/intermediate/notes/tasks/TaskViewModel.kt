package course.intermediate.notes.tasks

import androidx.lifecycle.ViewModel
import course.intermediate.notes.models.Task
import course.intermediate.notes.models.Todo

class TaskViewModel : ViewModel() {

    fun getFakeData(): List<Task> = mutableListOf(
        Task(
            "Testing 1", mutableListOf(
                Todo("Todo 1", true),
                Todo("Todo 2")
            )
        ),
        Task("Testing 2"),
        Task("Task Three", mutableListOf(
            Todo("Test A!"),
            Todo("Test B!")
        ))
    )
}