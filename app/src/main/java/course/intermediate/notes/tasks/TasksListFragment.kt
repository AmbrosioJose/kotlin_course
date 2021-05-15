package course.intermediate.notes.tasks

import android.os.Bundle
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import course.intermediate.notes.R
import course.intermediate.notes.models.Task
import course.intermediate.notes.models.Todo
import kotlinx.android.synthetic.main.fragment_notes_list.*
import androidx.lifecycle.ViewModelProvider
import course.intermediate.notes.tasks.TasksListFragment.TouchActionDelegate as TouchActionDelegate
import androidx.lifecycle.Observer

class TasksListFragment : Fragment() {

    lateinit var touchActionDelegate: TouchActionDelegate
    lateinit var viewModel: TaskViewModel
    lateinit var contentView: TaskListView

    override fun onAttach(context: Context){
        super.onAttach(context)
        context.let{
            if(context is TouchActionDelegate)
                touchActionDelegate = context
        }
    }

    override fun onResume(){
        super.onResume()
        viewModel.loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_tasks_list, container, false).apply{
            contentView = this as TaskListView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        setContentView()
    }

    private fun setContentView(){
        contentView.initView(touchActionDelegate, viewModel)
    }

    private fun bindViewModel(){
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        viewModel.taskListLiveData.observe(viewLifecycleOwner, Observer{ taskList ->
            contentView.updateList(taskList)
        })
    }

    companion object {
        fun newInstance() = TasksListFragment()
        /*
        * Same as statement above
        fun newInstance(): TasksListFragment {
            return TasksListFragment()
        }
        */
    }

    interface TouchActionDelegate {
        fun onAddButtonClicked(value: String)
    }
}