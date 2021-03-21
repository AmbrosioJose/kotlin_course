package course.intermediate.notes.database

import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update
import androidx.room.Query
import androidx.room.Dao

import course.intermediate.notes.models.Note

@Dao
interface NoteDAO {

    @Insert
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query ("SELECT * FROM notes")
    fun retrieveNotes(): MutableList<Note>
}