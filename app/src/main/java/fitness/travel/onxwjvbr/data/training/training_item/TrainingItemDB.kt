package fitness.travel.onxwjvbr.data.training.training_item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TrainingItemDB")
data class TrainingItemDB(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val trainingId:Int,
    val exerciseId:Int,
    val weight:Int,
    val amountRepeat:Int,
    val duration:Long,
)
