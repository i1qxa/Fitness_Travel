package fitness.travel.onxwjvbr.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exerciseitemdb WHERE bodyPart = :bodyPart")
    fun getListOfExerciseForBodyPart(bodyPart:String):LiveData<ExerciseItemDB>

}