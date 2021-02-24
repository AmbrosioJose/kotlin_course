package course.intermediate.notes.views

import androidx.constraintlayout.widget.ConstraintLayout
import android.content.Context
import android.util.AttributeSet
import course.intermediate.notes.models.Task
import kotlinx.android.synthetic.main.item_task.view.*
import android.view.LayoutInflater
import course.intermediate.notes.R
import android.graphics.Paint

class TaskView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var task: Task

    fun initView(task: Task, todoCheckedCallback: (Int, Boolean) -> Unit){
        this.task = task
        titleView.text = task.title
        task.todos.forEachIndexed{ todoIndex, todo ->
            val todoView = (LayoutInflater.from(context).inflate(R.layout.view_todo, todoContainer, false) as TodoView).apply{
                initView(todo) { isChecked ->

                    todoCheckedCallback.invoke(todoIndex, isChecked)
                    if(isTaskComplete())
                        this@TaskView.titleView.setStrikeThrough()
                    else
                        this@TaskView.titleView.removeStrikeThrough()
                }
            }
            todoContainer.addView(todoView)
        }
    }

    private fun isTaskComplete(): Boolean = task.todos.all { todo ->
      todo.isComplete
    }

}