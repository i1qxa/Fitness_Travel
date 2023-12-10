package fitness.travel.onxwjvbr.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi

@Database(
    entities = [
        ExerciseItemDB::class,
        MyExerciseItemDB::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class ExercisesDB:RoomDatabase() {
    abstract fun ExerciseDao(): ExerciseDao
    abstract fun MyExerciseDao(): MyExerciseDao


    companion object {
        private var INSTANCE: ExercisesDB? = null
        private val LOCK = Any()
        private const val DB_NAME = "exercises_db"

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(application: Application): ExercisesDB {
            INSTANCE?.let {
                return it
            }
            kotlinx.coroutines.internal.synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    ExercisesDB::class.java,
                    DB_NAME
                ).createFromAsset("exercises.db")
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}