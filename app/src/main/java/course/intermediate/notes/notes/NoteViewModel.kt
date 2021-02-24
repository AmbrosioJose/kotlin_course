package course.intermediate.notes.notes

import androidx.lifecycle.ViewModel
import course.intermediate.notes.models.Note
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class NoteViewModel : ViewModel(), NoteListViewContract {

    private val model: NoteModel = NoteModel()

    private val _notesLiveData: MutableLiveData<List<Note>> = MutableLiveData()
    val notesLiveData: LiveData<List<Note>> = _notesLiveData

    init {
        _notesLiveData.postValue(model.getFakeData())
    }
}