package course.intermediate.notes.foundations

import toothpick.Toothpick
import toothpick.config.Module
import course.intermediate.notes.notes.INoteModel
import course.intermediate.notes.notes.NoteLocalModel
import course.intermediate.notes.tasks.ITaskModel
import course.intermediate.notes.tasks.TaskLocalModel

object ApplicationScope {
    val scope = Toothpick.openScope(this).apply{
        installModules(ApplicationModule)
    }

}

object ApplicationModule: Module(){
    init{
        bind(INoteModel::class.java).toInstance(NoteLocalModel())
        bind(ITaskModel::class.java).toInstance(TaskLocalModel())
    }
}