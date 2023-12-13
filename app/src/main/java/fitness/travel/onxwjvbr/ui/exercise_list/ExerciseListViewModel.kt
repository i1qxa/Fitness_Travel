package fitness.travel.onxwjvbr.ui.exercise_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import fitness.travel.onxwjvbr.data.ExerciseItemDB
import fitness.travel.onxwjvbr.data.ExercisesDB
import kotlinx.coroutines.launch

class ExerciseListViewModel(application: Application) : AndroidViewModel(application) {

    val dao = ExercisesDB.getInstance(application).ExerciseDao()

    val myDao = ExercisesDB.getInstance(application).MyExerciseDao()

    val bodyPartLD = MutableLiveData<String>()

    val exercisesList = bodyPartLD.switchMap { bodyPart ->
        dao.getListOfExerciseForBodyPart(bodyPart)
    }
    val testLD = dao.getListOfExerciseForBodyPart("back")

    fun setBodyPart(bodyPart: Int) {
        bodyPartLD.value = when (bodyPart) {
            0 -> "back"
            1 -> "cardio"
            3 -> "chest"
            4 -> "lower arms"
            5 -> "lower legs"
            6 -> "neck"
            7 -> "shoulders"
            else -> "upper arms"
        }
    }

    fun changeExpanded(exerciseItem: ExerciseItemDB) {
        val isExpanded = if (exerciseItem.isExpanded == 0) 1 else 0
        viewModelScope.launch {
            dao.changeExpanded(exerciseItem.copy(isExpanded = isExpanded))
        }
    }

    fun addExerciseToMyList(item: ExerciseItemDB, day: Int) {
        viewModelScope.launch {
            myDao.addExerciseItem(item.toMyExerciseDBItem(day))
        }
    }

}