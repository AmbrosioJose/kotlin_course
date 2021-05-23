package course.intermediate.notes.tasks

import androidx.lifecycle.ViewModel
import course.intermediate.notes.models.Task
import course.intermediate.notes.tasks.ITaskModel
import course.intermediate.notes.models.Todo
import course.intermediate.notes.tasks.TaskLocalModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import javax.inject.Inject
import toothpick.Toothpick
import toothpick.config.Module
import course.intermediate.notes.foundations.ApplicationModule
import course.intermediate.notes.foundations.ApplicationScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel(), TaskListViewContract {

    @Inject
    lateinit var model: ITaskModel

    private val _taskListLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListLiveData

    init {
        Toothpick.inject(this, ApplicationScope.scope)
        loadData()
    }

    fun loadData() {
        GlobalScope.launch {
            model.retrieveTasks { tasksList ->
                tasksList?.let{
                    _taskListLiveData.postValue(it.toMutableList())
                }
            }
        }
    }
//        _taskListLiveData.postValue(model.retrieveTasks().toMutableList())

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        GlobalScope.launch {
            _taskListLiveData.value?.let{
                val todo = it[taskIndex].todos[todoIndex]
                todo.apply {
                    this.isComplete = isComplete
                    this.taskId = it[taskIndex].uid
                }
                model.updateTodo(todo) {
                    loadData()
                }
            }
        }
    }

    override fun onTaskDeleted(taskIndex: Int){
        println("deleting index!!!!: $taskIndex")
        GlobalScope.launch {
            _taskListLiveData.value?.let{
                model.deleteTask(it[taskIndex]) {
                    loadData()
                }
            }
        }
    }
}