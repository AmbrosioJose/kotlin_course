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
                        createStrikeThrough()
                    else
                        removeStrikeThrough()
                }
            }
            todoContainer.addView(todoView)
        }
    }

    fun isTaskComplete(): Boolean = task.todos.all { todo ->
      todo.isComplete
    }

    private fun createStrikeThrough(){
        titleView.apply{
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    private fun removeStrikeThrough(){
        titleView.apply{
            paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}