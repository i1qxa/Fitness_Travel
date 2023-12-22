package fitness.travel.onxwjvbr.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fitness.travel.onxwjvbr.data.exercise.ExerciseDao
import fitness.travel.onxwjvbr.data.exercise.ExerciseItemDB
import fitness.travel.onxwjvbr.data.my_exercise.MyExerciseDao
import fitness.travel.onxwjvbr.data.my_exercise.MyExerciseItemDB
import fitness.travel.onxwjvbr.data.training.TrainingDB
import fitness.travel.onxwjvbr.data.training.TrainingDao
import fitness.travel.onxwjvbr.data.training.training_item.TrainingItemDB
import fitness.travel.onxwjvbr.data.training.training_item.TrainingItemDao
import kotlinx.coroutines.InternalCoroutinesApi

@Database(
    entities = [
        ExerciseItemDB::class,
        MyExerciseItemDB::class,
        TrainingDB::class,
        TrainingItemDB::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class ExercisesDB:RoomDatabase() {
    abstract fun ExerciseDao(): ExerciseDao
    abstract fun MyExerciseDao(): MyExerciseDao

    abstract fun TrainingDao():TrainingDao

    abstract fun TrainingItemDao():TrainingItemDao


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