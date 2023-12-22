package fitness.travel.onxwjvbr.data.training

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TrainingDao {

    @Query("SELECT * FROM trainingdb ORDER BY date")
    fun getTrainings():LiveData<List<TrainingDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTraining(training:TrainingDB)

    @Delete
    suspend fun removeTrainingItem(training:TrainingDB)

}