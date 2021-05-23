package course.intermediate.notes.notes

import androidx.lifecycle.ViewModel
import course.intermediate.notes.models.Note
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import javax.inject.Inject
import toothpick.Toothpick
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import course.intermediate.notes.foundations.ApplicationScope

class NoteViewModel : ViewModel(), NoteListViewContract {

    @Inject
    lateinit var localModel: INoteModel

    private val _notesLiveData: MutableLiveData<List<Note>> = MutableLiveData()
    val notesLiveData: LiveData<List<Note>> = _notesLiveData

    init {
        
        Toothpick.inject(this, ApplicationScope.scope)
        loadData()
    }

    fun loadData() {
        GlobalScope.launch {
            localModel.retrieveNotes { nullableNotesList ->
                nullableNotesList?.let {
                    _notesLiveData.postValue(it)
                }
            }
        }
    }

    override fun onDeleteNote(note: Note){
        GlobalScope.launch {
            localModel.deleteNote(note){ deleted ->
                if(deleted)
                    loadData()
            }
        }
    }
}