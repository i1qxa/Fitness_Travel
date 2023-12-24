package fitness.travel.onxwjvbr.ui.my_exercise_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import fitness.travel.onxwjvbr.data.ExercisesDB
import fitness.travel.onxwjvbr.data.my_exercise.MyExerciseItemDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyExerciseListViewModel(application: Application):AndroidViewModel(application) {

    val myExercisesDao = ExercisesDB.getInstance(application).MyExerciseDao()

    val selectedDayLD = MutableLiveData<Int>()

    val myExercisesListLD = selectedDayLD.switchMap {
        myExercisesDao.getExerciseListForDayOfWeek(it)
    }

    fun changeDayOfWeek(day:Int){
        selectedDayLD.value = day
    }

    fun removeMyExerciseItem(item:MyExerciseItemDB){
        viewModelScope.launch(Dispatchers.IO) {
            myExercisesDao.removeExerciseItem(item)
        }
    }

}