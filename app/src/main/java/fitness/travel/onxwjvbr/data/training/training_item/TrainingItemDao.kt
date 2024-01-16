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

    @Query("SELECT * FROM trainingitemdb WHERE trainingId =:trainingId")
    fun getTrainingItemList(trainingId: Int): LiveData<List<TrainingItemDB>>

    @Query("SELECT trainingId, exerciseId, exerciseName, COUNT(*) as countRepeat, AVG(weight) as avgWeight, SUM(duration) as totalDuration FROM trainingitemdb WHERE trainingId =:trainingId GROUP BY exerciseId")
    fun getTrainingItemListCommon(trainingId: Int): LiveData<List<TrainingCommonInfo>>

    @Query("SELECT * FROM trainingitemdb WHERE trainingId =:trainingId AND exerciseId =:exerciseId")
    fun getExerciseInTraining(trainingId: Int, exerciseId:Int):LiveData<List<TrainingItemDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrainingItem(item: TrainingItemDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListOfExerciseItems(itemsList:List<TrainingItemDB>)


}