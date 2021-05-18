package course.intermediate.notes.tasks

import course.intermediate.notes.models.Task
import javax.inject.Inject
import course.intermediate.notes.application.NoteApplication
import course.intermediate.notes.database.RoomDatabaseClient
import course.intermediate.notes.models.Todo
import course.intermediate.notes.notes.TIMEOUT_DURATION_MILLIS
import kotlinx.coroutines.launch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.Job

class TaskLocalModel @Inject constructor(): ITaskModel {

    private var databaseClient: RoomDatabaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    private fun performOperationWithTimeout(function: () -> Unit, callback: SuccessCallback) {
        GlobalScope.launch {
            val job = async {
                try {
                    withTimeout(TIMEOUT_DURATION_MILLIS){
                        function.invoke()
                    }
                } catch (e: java.lang.Exception) {
                    callback.invoke(false)
                }
            }
            job.await()
            callback.invoke(true)
        }
    }

    override fun getFakeData(): MutableList<Task> = retrieveTasks().toMutableList()

    override fun addTask(task: Task, callback: SuccessCallback){
        GlobalScope.launch {
            val masterJob = async {
                // adds task entity component
                try {
                    databaseClient.taskDAO().addTask(task)
                } catch (e: Exception){
                    callback.invoke(false)
                }
                addTodosJob(task)
            }

        }
//        databaseClient.taskDAO().addTask(task)
//        addTodosInTask(task)
//        callback.invoke(true)
    }

    override fun addTodosJob(task: Task): Job = GlobalScope.async {
        task.todos.forEach {
            databaseClient.taskDAO().addTodo(it)
        }
    }

    override fun updateTask(task: Task, callback: SuccessCallback){
        performOperationWithTimeout({databaseClient.taskDAO().updateTask(task)}, callback)
    }

    override fun updateTodo(todo: Todo, callback: SuccessCallback){
        performOperationWithTimeout({databaseClient.taskDAO().updateTodo(todo)}, callback)
    }

    override fun deleteTask(task: Task, callback: SuccessCallback){
        performOperationWithTimeout({databaseClient.taskDAO().deleteTask(task)}, callback)
    }

//    private fun addTodos

//    private fun addTodosInTask(task: Task){
//        task.todos.forEach { todo ->
//            databaseClient.taskDAO().addTodo(todo)
//        }
//    }

    override fun retrieveTasks(callback: (List<Task>?) -> Unit) {
        GlobalScope.launch {
            val job = async {
                withTimeoutOrNull(TIMEOUT_DURATION_MILLIS){
                    databaseClient.taskDAO().retrieveTasks()
                }
            }
        }
    }
}