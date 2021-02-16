package course.intermediate.notes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import course.intermediate.notes.R
import course.intermediate.notes.foundations.BaseRecyclerAdapter
import course.intermediate.notes.models.Note
import kotlinx.android.synthetic.main.item_note.view.*
import course.intermediate.notes.views.NoteView

class NotesAdapter(
    notesList: MutableList<Note> = mutableListOf()
) : BaseRecyclerAdapter<Note>(notesList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
        = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))


    class ViewHolder(view : View): BaseViewHolder<Note>(view) {
        override fun onBind(data: Note) {
            (view as NoteView).initView(data)
        }
    }
}