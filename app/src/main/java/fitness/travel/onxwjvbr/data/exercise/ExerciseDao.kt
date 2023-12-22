package fitness.travel.onxwjvbr.data.exercise

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exerciseitemdb WHERE bodyPart = :bodyPart")
    fun getListOfExerciseForBodyPart(bodyPart:String):LiveData<List<ExerciseItemDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun changeExpanded(item:ExerciseItemDB)

}