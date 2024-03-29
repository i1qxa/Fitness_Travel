package fitness.travel.onxwjvbr.data.my_exercise

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyExerciseDao {

    @Query("SELECT * FROM MyExerciseItemDB WHERE dayOfWeek =:dayOfWeek")
    fun getExerciseListForDayOfWeek(dayOfWeek:Int):LiveData<List<MyExerciseItemDB>>

    @Query("SELECT * FROM MyExerciseItemDB WHERE dayOfWeek =:dayOfWeek")
    suspend fun getExerciseList(dayOfWeek:Int):List<MyExerciseItemDB>

    @Delete
    suspend fun removeExerciseItem(exerciseItem:MyExerciseItemDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExerciseItem(exerciseItem:MyExerciseItemDB)

}