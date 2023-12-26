package fitness.travel.onxwjvbr.data.training

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.room.Entity
import androidx.room.PrimaryKey
import fitness.travel.onxwjvbr.R

@Entity(tableName = "TrainingDB")
data class TrainingDB(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val duration:Long,
    val date:Long,
    val dayOfWeek:Int,
){
    fun getFormattedDate():String{
        val time = Calendar.getInstance()
        time.timeInMillis = date
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        return formatter.format(time)
    }

    fun getFormattedDuration():String{
        val formater = SimpleDateFormat("HH:mm:ss")
        return formater.format(duration)
    }

    fun getDayAsString():String{
        return when(dayOfWeek){
            1 -> "Monday"
            2 -> "Tuesday"
            3 -> "Wednesday"
            4 -> "Thursday"
            5 -> "Friday"
            6 -> "Saturday"
            else -> "Sunday"
        }
    }

}
