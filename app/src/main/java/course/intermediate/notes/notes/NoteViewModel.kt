package course.intermediate.notes.notes

import androidx.lifecycle.ViewModel
import course.intermediate.notes.models.Note
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import javax.inject.Inject
import toothpick.Toothpick
import toothpick.config.Module
import course.intermediate.notes.foundations.ApplicationModule
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
        _notesLiveData.postValue(localModel.getFakeData())
    }

    override fun onDeleteNote(note: Note){
        localModel.deleteNote(note){ deleted ->
            if(deleted)
                loadData()
        }
    }
}