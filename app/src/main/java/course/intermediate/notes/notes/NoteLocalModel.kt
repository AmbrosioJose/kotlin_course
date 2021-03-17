package course.intermediate.notes.notes

import course.intermediate.notes.models.Note
import course.intermediate.notes.notes.INoteModel
import javax.inject.Inject
import android.util.Log

class NoteLocalModel @Inject constructor() : INoteModel{

    override fun getFakeData(): MutableList<Note> = mutableListOf(
        Note("Hey keep working hard. It will Pay off."),
        Note("you got this!! Keep going"),
        Note("Life is good"))

    override fun addNote(note: Note, callback: SuccessCallback){
        Log.d("UdemyCourse", note.toString())
        callback.invoke(true)
    }

    override fun updateNote(note: Note, callback: SuccessCallback){

    }

    override fun deleteNote(note: Note, callback: SuccessCallback){

    }

    override fun retrieveNotes(): List<Note>{
        TODO("not implemented")
    }
}