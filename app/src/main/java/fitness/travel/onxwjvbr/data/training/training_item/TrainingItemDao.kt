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

    @Query("SELECT * FROM trainingitemdb item LEFT JOIN trainingdb training ON item.trainingId = training.id WHERE item.trainingId =:trainingId GROUP BY item.exerciseId")
    fun getTrainingItemList(trainingId:Int):LiveData<List<TrainingItemWithName>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrainingItem(item:TrainingItemDB)



}