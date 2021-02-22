package course.intermediate.notes.tasks

import androidx.lifecycle.ViewModel
import course.intermediate.notes.models.Task
import course.intermediate.notes.models.Todo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class TaskViewModel : ViewModel(), TaskListViewContract {
    private val _taskListLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListLiveData

    init {
        _taskListLiveData.postValue(getFakeData())
    }

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

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean){
        _taskListLiveData.value?.get(taskIndex)?.todos?.get(todoIndex)?.isComplete = isComplete
    }
}