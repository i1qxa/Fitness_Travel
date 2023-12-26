package fitness.travel.onxwjvbr.ui.training_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fitness.travel.onxwjvbr.data.ExercisesDB

class TrainingListViewModel(application: Application):AndroidViewModel(application) {

    val dao = ExercisesDB.getInstance(application).TrainingDao()

    val trainingList = dao.getTrainings()

}