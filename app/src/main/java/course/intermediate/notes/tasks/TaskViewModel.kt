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

    private val _stateChangeLiveData: MutableLiveData<ItemState> = MutableLiveData()
    val stateChangedLiveData: LiveData<ItemState> = _stateChangeLiveData

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
            _taskListLiveData.value?.let{ taskList ->
                val todo = taskList[taskIndex].todos[todoIndex]
                todo.apply {
                    this.isComplete = isComplete
                    this.taskId = taskList[taskIndex].uid
                }
                model.updateTodo(todo) { success ->
                    if(success) {
                        _stateChangeLiveData.postValue(ItemState.ItemUpdated(
                            newTask = taskList[taskIndex],
                            il = taskIndex,
                            iv = taskIndex + 1
                        ))
                    }
//                    loadData()
                }
            }
        }
    }

    override fun onTaskDeleted(taskIndex: Int){
        println("deleting index!!!!: $taskIndex")
        GlobalScope.launch {
            _taskListLiveData.value?.let{ taskList ->
                model.deleteTask(taskList[taskIndex]) { success ->
//                    loadData()
                    if(success) {
                        _stateChangeLiveData.postValue(ItemState.ItemDeleted(
                            il = taskIndex,
                            iv = taskIndex + 1
                        ))
                    }
                }
            }
        }
    }

    sealed class ItemState(val indexInList: Int, val indexInView: Int) {
        class ItemUpdated(val newTask: Task, il: Int, iv: Int) : ItemState(il, iv)
        class ItemDeleted(il: Int, iv: Int): ItemState(il, iv)
    }
}