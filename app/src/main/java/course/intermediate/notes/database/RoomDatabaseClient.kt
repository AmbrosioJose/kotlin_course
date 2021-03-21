package course.intermediate.notes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import android.content.Context
import course.intermediate.notes.models.Task
import course.intermediate.notes.models.Todo
import course.intermediate.notes.models.Note
import course.intermediate.notes.models.Tag
import course.intermediate.notes.models.TaskEntity

const val DATABASE_SCHEMA_VERSION = 1
const val DB_NAME = "local-db"

@Database(version = DATABASE_SCHEMA_VERSION, entities = [TaskEntity::class, Todo::class, Tag::class, Note::class])
abstract class RoomDatabaseClient : RoomDatabase(){

    // Insert DAOs below
    abstract fun noteDAO(): NoteDAO
    abstract fun taskDAO(): TaskDAO

    companion object {
        private var instance: RoomDatabaseClient? = null

        fun getInstance(context: Context): RoomDatabaseClient {
            if(instance == null){
                instance = createDatabase(context)
            }
            return instance!!
        }

        private fun createDatabase(context: Context): RoomDatabaseClient {
            return Room.databaseBuilder(context, RoomDatabaseClient::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
        }
    }



}