package course.intermediate.notes.tasks

import course.intermediate.notes.models.Task
import course.intermediate.notes.models.Todo
import javax.inject.Inject

class TaskLocalModel @Inject constructor(): ITaskModel {

    override fun getFakeData(): MutableList<Task> = mutableListOf(
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

    override fun addTask(task: Task, callback: SuccessCallback){

    }

    override fun updateTask(task: Task, callback: SuccessCallback){

    }

    override fun deleteTask(task: Task, callback: SuccessCallback){

    }

    override fun retrieveTasks(): List<Task>{
        TODO("not implemented")
    }
}