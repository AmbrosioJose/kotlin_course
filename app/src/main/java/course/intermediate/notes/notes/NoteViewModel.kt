package course.intermediate.notes.notes

import androidx.lifecycle.ViewModel
import course.intermediate.notes.models.Note
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import javax.inject.Inject
import toothpick.Toothpick

class NoteViewModel : ViewModel(), NoteListViewContract {

    @Inject
    lateinit var model: NoteModel

    private val _notesLiveData: MutableLiveData<List<Note>> = MutableLiveData()
    val notesLiveData: LiveData<List<Note>> = _notesLiveData

    init {
        val scope = Toothpick.openScope(this)
        Toothpick.inject(this, scope)
        _notesLiveData.postValue(model.getFakeData())
    }
}