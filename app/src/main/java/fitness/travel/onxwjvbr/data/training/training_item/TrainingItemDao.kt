package fitness.travel.onxwjvbr.data.training.training_item

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fitness.travel.onxwjvbr.domain.TrainingItemWithName

@Dao
interface TrainingItemDao {

    //    @Query("SELECT item.id, item.trainingId, item.exerciseId, exercise.name, item.weight, item.amountRepeat, item.duration   FROM trainingitemdb item LEFT JOIN exerciseitemdb exercise ON item.exerciseId = exercise.id WHERE item.trainingId =:trainingId GROUP BY item.exerciseId")
//    fun getTrainingItemList(trainingId:Int):LiveData<List<TrainingItemWithName>>
    @Query("SELECT * FROM trainingitemdb WHERE trainingId =:trainingId")
    fun getTrainingItemList(trainingId: Int): LiveData<List<TrainingItemDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrainingItem(item: TrainingItemDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListOfExerciseItems(itemsList:List<TrainingItemDB>)


}