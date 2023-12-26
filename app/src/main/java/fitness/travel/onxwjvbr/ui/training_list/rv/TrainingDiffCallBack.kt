package fitness.travel.onxwjvbr.ui.training_list.rv

import androidx.recyclerview.widget.DiffUtil
import fitness.travel.onxwjvbr.data.exercise.ExerciseItemDB
import fitness.travel.onxwjvbr.data.training.TrainingDB
import fitness.travel.onxwjvbr.data.training.training_item.TrainingItemDB

class TrainingDiffCallBack:DiffUtil.ItemCallback<TrainingDB>() {

    override fun areItemsTheSame(oldItem: TrainingDB, newItem: TrainingDB): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TrainingDB, newItem: TrainingDB): Boolean {
        return oldItem == newItem
    }
}