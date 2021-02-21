package course.intermediate.notes.notes

import android.os.Bundle
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import course.intermediate.notes.R
import course.intermediate.notes.models.Note
import kotlinx.android.synthetic.main.fragment_notes_list.*

class NotesListFragment : Fragment() {

    lateinit var touchActionDelegate: TouchActionDelegate
    override fun onAttach(context: Context){
        super.onAttach(context)

        context?.let{
            if(it is TouchActionDelegate){
                touchActionDelegate = it
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = NotesAdapter(mutableListOf(
            Note("Description 1"),
            Note("Description 2"),
            Note("Description 3")),
            touchActionDelegate
        )

        recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance() = NotesListFragment()

    }

    interface TouchActionDelegate{
        fun onAddButtonClicked(value: String)
    }


}