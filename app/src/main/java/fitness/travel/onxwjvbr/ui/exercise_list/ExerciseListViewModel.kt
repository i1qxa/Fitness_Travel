package fitness.travel.onxwjvbr.ui.exercise_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import fitness.travel.onxwjvbr.data.ExerciseItemDB
import fitness.travel.onxwjvbr.data.ExercisesDB
import kotlinx.coroutines.launch

class ExerciseListViewModel(application: Application):AndroidViewModel(application) {

    val dao = ExercisesDB.getInstance(application).ExerciseDao()

    val myDao = ExercisesDB.getInstance(application).MyExerciseDao()

    val bodyPartLD = MutableLiveData<String>()

    val exercisesList = bodyPartLD.switchMap { bodyPart ->
        dao.getListOfExerciseForBodyPart(bodyPart)
    }

    fun setBodyPart(bodyPart:String){
        bodyPartLD.value = bodyPart
    }

    fun addExerciseToMyList(item:ExerciseItemDB, day:Int){
        viewModelScope.launch {
            myDao.addExerciseItem(item.toMyExerciseDBItem(day))
        }
    }

}