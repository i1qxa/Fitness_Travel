package fitness.travel.onxwjvbr.ui.training_item.full.rv

import androidx.recyclerview.widget.DiffUtil
import fitness.travel.onxwjvbr.data.training.training_item.TrainingCommonInfo
import fitness.travel.onxwjvbr.data.training.training_item.TrainingItemDB

class ItemDiffCallBack:DiffUtil.ItemCallback<TrainingItemDB>() {

    override fun areItemsTheSame(oldItem: TrainingItemDB, newItem: TrainingItemDB): Boolean {
        return oldItem.exerciseId == newItem.exerciseId
    }

    override fun areContentsTheSame(oldItem: TrainingItemDB, newItem: TrainingItemDB): Boolean {
        return oldItem == newItem
    }
}