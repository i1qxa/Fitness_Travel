package fitness.travel.onxwjvbr.ui.training_item

import android.app.Application
import android.icu.text.SimpleDateFormat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import fitness.travel.onxwjvbr.data.ExercisesDB
import fitness.travel.onxwjvbr.data.training.TrainingDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

class TrainingItemViewModel(application: Application) : AndroidViewModel(application) {

    val dao = ExercisesDB.getInstance(application).TrainingItemDao()

    val trainingDao = ExercisesDB.getInstance(application).TrainingDao()

    var isTrainingFinish = false

    val shouldFinishTraining = MutableLiveData<Boolean>()

    val trainingIdLD = MutableLiveData<Int>()

    val exerciseListLD = trainingIdLD.switchMap {
        dao.getTrainingItemListCommon(it)
    }


    val timerLD = MutableLiveData<String>()

    var trainingDuration: Long = 0

    val formater = SimpleDateFormat("HH:mm:ss")

    fun finishTraining() {
        viewModelScope.launch(Dispatchers.IO) {
            trainingDao.addTraining(
                (trainingDao.getTrainingById(
                    trainingIdLD.value?.toLong() ?: 1
                )).copy(duration = trainingDuration)
            )
            isTrainingFinish = true
            shouldFinishTraining.postValue(true)
        }
    }

    fun startNewTraining() {
        val newTraining = TrainingDB(0, 0, Calendar.getInstance().timeInMillis, 2)
        viewModelScope.launch {
            trainingIdLD.postValue(trainingDao.addTraining(newTraining).toInt())
        }
    }

    init {
        viewModelScope.launch {
            while (!isTrainingFinish) {
                delay(1000)
                trainingDuration += 1000
                timerLD.postValue(formater.format(trainingDuration))
            }
        }
    }


}