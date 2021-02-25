package course.intermediate.notes.tasks

import course.intermediate.notes.models.Task
import course.intermediate.notes.models.Todo
import javax.inject.Inject

class TaskModel @Inject constructor() {

    fun getFakeData(): MutableList<Task> = mutableListOf(
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