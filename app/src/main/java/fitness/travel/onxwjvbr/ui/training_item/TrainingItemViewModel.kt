package fitness.travel.onxwjvbr.ui.training_item

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.util.Calendar

class TrainingItemViewModel(application: Application):AndroidViewModel(application) {

    var startTime = Calendar.getInstance().timeInMillis

    val timerLD = MutableLiveData<String>()

    var timer = 0

    fun convertIntToString(timeInSeconds:Int):String{
        var seconds
        val minutes1 = timeInSeconds/600
        val minutes2 =
    }

}