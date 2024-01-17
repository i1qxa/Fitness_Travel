package fitness.travel.onxwjvbr.ui.training_chart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fitness.travel.onxwjvbr.data.ExercisesDB

class TrainingChartViewModel(application: Application):AndroidViewModel(application) {

    private val dao = ExercisesDB.getInstance(application).TrainingItemDao()

    val itemList = dao.getExerciseCount()

}