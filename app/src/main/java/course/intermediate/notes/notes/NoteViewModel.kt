package course.intermediate.notes.notes

import androidx.lifecycle.ViewModel
import course.intermediate.notes.models.Note
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class NoteViewModel : ViewModel(), NoteListViewContract {

    private val _notesLiveData: MutableLiveData<List<Note>> = MutableLiveData()
    val notesLiveData: LiveData<List<Note>> = _notesLiveData

    init {
        _notesLiveData.postValue(getFakeData())
    }

    fun getFakeData(): MutableList<Note> = mutableListOf(
        Note("Hey keep working hard. It will Pay off."),
        Note("you got this!! Keep going"),
        Note("Life is good"))
}