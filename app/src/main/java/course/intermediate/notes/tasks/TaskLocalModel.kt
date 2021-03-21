package course.intermediate.notes.tasks

import course.intermediate.notes.models.Task
import course.intermediate.notes.models.Todo
import javax.inject.Inject
import android.util.Log
import course.intermediate.notes.application.NoteApplication
import course.intermediate.notes.database.RoomDatabaseClient

class TaskLocalModel @Inject constructor(): ITaskModel {

    private var databaseClient: RoomDatabaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    override fun getFakeData(): MutableList<Task> = mutableListOf(
        Task(
            "Testing 1", mutableListOf(
                Todo(description = "Todo 1", isComplete = true),
                Todo(description = "Todo 2")
            )
        ),
        Task("Testing 2"),
        Task("Task Three", mutableListOf(
            Todo(description = "Test A!"),
            Todo(description = "Test B!")
        ))
    )

    override fun addTask(task: Task, callback: SuccessCallback){
        Log.d("CourseTag", task.toString())
        callback.invoke(true)
    }

    override fun updateTask(task: Task, callback: SuccessCallback){

    }

    override fun deleteTask(task: Task, callback: SuccessCallback){

    }

    override fun retrieveTasks(): List<Task>{
        TODO("not implemented")
    }
}