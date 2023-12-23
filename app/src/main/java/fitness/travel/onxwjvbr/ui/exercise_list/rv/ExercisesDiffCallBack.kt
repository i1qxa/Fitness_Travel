package fitness.travel.onxwjvbr.ui.exercise_list.rv

import androidx.recyclerview.widget.DiffUtil
import fitness.travel.onxwjvbr.data.exercise.ExerciseItemDB

class ExercisesDiffCallBack:DiffUtil.ItemCallback<ExerciseItemDB>() {

    override fun areItemsTheSame(oldItem: ExerciseItemDB, newItem: ExerciseItemDB): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExerciseItemDB, newItem: ExerciseItemDB): Boolean {
        return oldItem == newItem
    }
}