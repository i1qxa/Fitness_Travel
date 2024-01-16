package fitness.travel.onxwjvbr.ui.training_item.full

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import fitness.travel.onxwjvbr.data.ExercisesDB
import fitness.travel.onxwjvbr.data.training.training_item.TrainingInfo
import fitness.travel.onxwjvbr.data.training.training_item.TrainingItemDB
import kotlinx.coroutines.launch

class TrainingItemFullViewModel(application: Application):AndroidViewModel(application) {

    var startTime:Long = 0
    var endTime:Long = 0
    var trainingInfo:TrainingInfo? = null
    var exerciseName = ""
    val dao = ExercisesDB.getInstance(application).TrainingItemDao()
    val trainingInfoLD = MutableLiveData<TrainingInfo>()

    val exerciseList = trainingInfoLD.switchMap {
        dao.getExerciseInTraining(it.trainingId, it.exerciseId)
    }

    fun setupTrainingInfo(trainingId:Int, exerciseId:Int, exerciseName1:String){
        trainingInfo = TrainingInfo(trainingId, exerciseId)
        trainingInfoLD.value = trainingInfo
        exerciseName = exerciseName1
    }

    fun addNewExercise(weight:Int, repeat:Int){
        val exercise = TrainingItemDB(
            id = 0,
            amountRepeat = repeat,
            duration = (endTime - startTime),
            exerciseId = trainingInfo?.exerciseId?:0,
            exerciseName = exerciseName,
            trainingId = trainingInfo?.trainingId?:0,
            weight = weight
        )
        viewModelScope.launch {
            dao.addTrainingItem(exercise)
            startTime = 0
            endTime = 0
        }
    }

}