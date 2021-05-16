package course.intermediate.notes.notes

import androidx.constraintlayout.widget.ConstraintLayout
import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_notes_list.view.*
import course.intermediate.notes.models.Note

class NoteListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 1
): ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var adapter: NotesAdapter
    private lateinit var touchActionDelegate: NotesListFragment.TouchActionDelegate
    private lateinit var dataActionDelegate: NoteListViewContract

    fun initView(touchActionDelegate: NotesListFragment.TouchActionDelegate, daDelegate: NoteListViewContract){
        resetChildViews()
        setDelegates(touchActionDelegate, daDelegate)
        setUpView()
    }

    private fun resetChildViews(){
        recyclerView.removeAllViewsInLayout()
    }

    fun setDelegates(touchActionDelegate: NotesListFragment.TouchActionDelegate, daDelegate: NoteListViewContract){
        this.touchActionDelegate = touchActionDelegate
        this.dataActionDelegate = daDelegate
    }

    fun setUpView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = NotesAdapter(touchActionDelegate = touchActionDelegate, dataActionDelegate = dataActionDelegate)
        recyclerView.adapter = adapter
    }

    fun updateList(list: List<Note>){
        adapter.updateList(list)
    }
}