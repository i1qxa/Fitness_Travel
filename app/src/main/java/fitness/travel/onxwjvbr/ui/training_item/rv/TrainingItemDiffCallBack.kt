package fitness.travel.onxwjvbr.ui.training_item.rv

import androidx.recyclerview.widget.DiffUtil
import fitness.travel.onxwjvbr.data.training.TrainingDB
import fitness.travel.onxwjvbr.data.training.training_item.TrainingItemDB

class TrainingItemDiffCallBack:DiffUtil.ItemCallback<TrainingItemDB>() {

    override fun areItemsTheSame(oldItem: TrainingItemDB, newItem: TrainingItemDB): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TrainingItemDB, newItem: TrainingItemDB): Boolean {
        return oldItem == newItem
    }
}