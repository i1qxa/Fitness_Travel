package fitness.travel.onxwjvbr.ui.training_item.rv

import androidx.recyclerview.widget.DiffUtil
import fitness.travel.onxwjvbr.data.training.TrainingDB
import fitness.travel.onxwjvbr.data.training.training_item.TrainingCommonInfo
import fitness.travel.onxwjvbr.data.training.training_item.TrainingItemDB

class TrainingItemDiffCallBack:DiffUtil.ItemCallback<TrainingCommonInfo>() {

    override fun areItemsTheSame(oldItem: TrainingCommonInfo, newItem: TrainingCommonInfo): Boolean {
        return oldItem.exerciseId == newItem.exerciseId
    }

    override fun areContentsTheSame(oldItem: TrainingCommonInfo, newItem: TrainingCommonInfo): Boolean {
        return oldItem == newItem
    }
}