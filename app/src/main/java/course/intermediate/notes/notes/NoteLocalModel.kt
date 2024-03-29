package course.intermediate.notes.notes

import course.intermediate.notes.models.Note
import javax.inject.Inject
import course.intermediate.notes.application.NoteApplication
import course.intermediate.notes.database.RoomDatabaseClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

const val TIMEOUT_DURATION_MILLIS = 3000L

class NoteLocalModel @Inject constructor() : INoteModel{

    private var databaseClient: RoomDatabaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    private suspend fun performOperationWithTimeout(function: () -> Unit, callback: SuccessCallback){
      GlobalScope.launch {
          val job = async {
              try {
                  withTimeout(TIMEOUT_DURATION_MILLIS) {
                      function.invoke()
                  }
                  true
              } catch(e: java.lang.Exception) {
                  false
              }
          }
          callback.invoke(job.await())
      }
    }

//    override fun getFakeData(): MutableList<Note> = retrieveNotes().toMutableList()
//        mutableListOf(
//        Note(description = "Hey keep working hard. It will Pay off."),
//        Note(description = "you got this!! Keep going"),
//        Note(description = "Life is good"))

    override suspend fun addNote(note: Note, callback: SuccessCallback){
        performOperationWithTimeout({databaseClient.noteDAO().addNote(note)}, callback)
    }

    override suspend fun updateNote(note: Note, callback: SuccessCallback){
        performOperationWithTimeout({
            databaseClient.noteDAO().updateNote(note)
        }, callback)
    }

    override suspend fun deleteNote(note: Note, callback: SuccessCallback){
        performOperationWithTimeout({databaseClient.noteDAO().deleteNote(note)}, callback)
    }

    override suspend fun retrieveNotes(callback: (List<Note>?) -> Unit){
        val job = GlobalScope.async {
            withTimeoutOrNull(TIMEOUT_DURATION_MILLIS){
                databaseClient.noteDAO().retrieveNotes()
            }
        }
        callback.invoke(job.await())
    }
}