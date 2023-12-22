package fitness.travel.onxwjvbr.data.training

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TrainingDB")
data class TrainingDB(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val duration:Long,
    val date:Long,
)
