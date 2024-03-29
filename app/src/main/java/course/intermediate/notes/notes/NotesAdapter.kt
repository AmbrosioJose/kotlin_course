package course.intermediate.notes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import course.intermediate.notes.R
import course.intermediate.notes.foundations.BaseRecyclerAdapter
import course.intermediate.notes.models.Note
import course.intermediate.notes.navigation.NavigationActivity
import kotlinx.android.synthetic.main.item_note.view.*
import course.intermediate.notes.views.NoteView
import kotlinx.android.synthetic.main.view_add_button.view.*
import course.intermediate.notes.notes.NotesListFragment.TouchActionDelegate

class NotesAdapter(
    notesList: MutableList<Note> = mutableListOf(),
    val touchActionDelegate: TouchActionDelegate,
    val dataActionDelegate: NoteListViewContract
) : BaseRecyclerAdapter<Note>(notesList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if(viewType == TYPE_ADD_BUTTON)
            AddButtonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_add_button, parent, false))
        else
            NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))


    inner class NoteViewHolder(view : View): BaseViewHolder<Note>(view) {
        override fun onBind(data: Note, listIndex: Int) {
            (view as NoteView).initView(data) {
                dataActionDelegate.onDeleteNote(masterList[listIndex])
            }
        }
    }

    inner class AddButtonViewHolder(view: View): BaseRecyclerAdapter.AddButtonViewHolder(view){
        override fun onBind(data: Unit, listIndex: Int){
            view.buttonText.text = view.context.getText(R.string.add_button_note)

            view.setOnClickListener{
                touchActionDelegate.onAddButtonClicked(NavigationActivity.FRAGMENT_VALUE_NOTE)
            }
        }
    }
}