package fitness.travel.onxwjvbr.ui.my_exercise_list

import android.app.Application
import android.icu.util.Calendar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import fitness.travel.onxwjvbr.data.ExercisesDB
import fitness.travel.onxwjvbr.data.my_exercise.MyExerciseItemDB
import fitness.travel.onxwjvbr.data.training.TrainingDB
import fitness.travel.onxwjvbr.data.training.training_item.TrainingItemDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyExerciseListViewModel(application: Application) : AndroidViewModel(application) {

    val db = ExercisesDB.getInstance(application)

    val myExercisesDao = db.MyExerciseDao()

    val trainingDao = db.TrainingDao()

    val trainingItemDao = db.TrainingItemDao()

    val newTrainingId = MutableLiveData<Int?>()

    val selectedDayLD = MutableLiveData<Int>()

    val myExercisesListLD = selectedDayLD.switchMap {
        myExercisesDao.getExerciseListForDayOfWeek(it)
    }

    fun changeDayOfWeek(day: Int) {
        selectedDayLD.value = day
    }

    fun removeMyExerciseItem(item: MyExerciseItemDB) {
        viewModelScope.launch(Dispatchers.IO) {
            myExercisesDao.removeExerciseItem(item)
        }
    }

    fun clearNewTrainingId(){
        newTrainingId.value = null
    }

    fun startNewTraining() {
        viewModelScope.launch(Dispatchers.IO) {
            val newTraining = TrainingDB(
                0,
                0,
                Calendar.getInstance().timeInMillis,
                (selectedDayLD.value ?: 1) -1
            )
            val trainingId = trainingDao.addTraining(newTraining).toInt()
            newTrainingId.postValue(trainingId)
            val listOfExercises = myExercisesDao.getExerciseList(selectedDayLD.value ?: 1)
            trainingItemDao.addListOfExerciseItems(listOfExercises.map {
                TrainingItemDB(
                    id = 0,
                    duration = 0,
                    trainingId = trainingId,
                    exerciseId = it.id,
                    weight = 0,
                    amountRepeat = 0,
                    exerciseName = it.name
                )
            }
            )
        }
    }

}