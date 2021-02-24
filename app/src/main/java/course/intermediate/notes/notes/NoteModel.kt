package course.intermediate.notes.notes

import course.intermediate.notes.models.Note

class NoteModel {

    fun getFakeData(): MutableList<Note> = mutableListOf(
        Note("Hey keep working hard. It will Pay off."),
        Note("you got this!! Keep going"),
        Note("Life is good"))
}