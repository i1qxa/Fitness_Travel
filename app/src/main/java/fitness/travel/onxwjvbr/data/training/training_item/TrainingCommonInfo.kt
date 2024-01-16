package fitness.travel.onxwjvbr.data.training.training_item

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar

data class TrainingCommonInfo(
    val trainingId:Int,
    val exerciseId:Int,
    val exerciseName:String,
    val countRepeat:Int,
    val avgWeight:Int,
    val totalDuration:Long,
){
    fun getFormattedDuration():String{
        val time = Calendar.getInstance()
        time.timeInMillis = totalDuration
        val formatter = SimpleDateFormat("mm:ss")
        return formatter.format(time)
    }
}
