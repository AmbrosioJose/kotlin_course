package course.intermediate.notes.tasks

import android.os.Bundle
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import course.intermediate.notes.R
import course.intermediate.notes.models.Task
import course.intermediate.notes.models.Todo
import kotlinx.android.synthetic.main.fragment_notes_list.*

class TasksListFragment : Fragment() {

    lateinit var touchActionDelegate: TouchActionDelegate

    override fun onAttach(context: Context){
        super.onAttach(context)
        context?.let{
            if(it is TouchActionDelegate)
                touchActionDelegate = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tasks_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = TaskAdapter(mutableListOf(
            Task("Testing 1", mutableListOf(
                Todo("Todo 1", true),
                Todo("Todo 2")
            )),
            Task("Testing 2")
        ),
        touchActionDelegate)

        recyclerView.adapter = adapter
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