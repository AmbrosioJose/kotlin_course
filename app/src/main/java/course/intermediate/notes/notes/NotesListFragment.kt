package course.intermediate.notes.notes

import android.os.Bundle
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import course.intermediate.notes.R
import course.intermediate.notes.models.Note
import kotlinx.android.synthetic.main.fragment_notes_list.*
import course.intermediate.notes.notes.NoteViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer


class NotesListFragment : Fragment() {

    lateinit var touchActionDelegate: TouchActionDelegate
    private lateinit var viewModel: NoteViewModel
    private lateinit var contentView: NoteListView

    override fun onAttach(context: Context){
        super.onAttach(context)
        context.let{
            if(context is TouchActionDelegate){
                touchActionDelegate = context
            }
        }
    }


    override fun onResume(){
        super.onResume()
        viewModel.loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes_list, container, false).apply{
            contentView = this as NoteListView
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
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        viewModel.notesLiveData.observe(viewLifecycleOwner, Observer{notesList ->
            contentView.updateList(notesList)
        })
    }

    companion object {
        fun newInstance() = NotesListFragment()

    }

    interface TouchActionDelegate{
        fun onAddButtonClicked(value: String)
    }


}