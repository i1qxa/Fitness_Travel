package fitness.travel.onxwjvbr.data.training.training_item

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
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
    val exerciseName:String,
){
    fun getFormattedDuration():String{
        val time = Calendar.getInstance()
        time.timeInMillis = duration
        val formatter = SimpleDateFormat("mm:ss")
        return formatter.format(time)
    }
}
