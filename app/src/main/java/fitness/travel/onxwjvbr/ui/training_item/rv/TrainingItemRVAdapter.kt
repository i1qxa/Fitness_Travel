package fitness.travel.onxwjvbr.ui.training_item.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.data.training.training_item.TrainingCommonInfo
import fitness.travel.onxwjvbr.data.training.training_item.TrainingItemDB
import fitness.travel.onxwjvbr.ui.firstCharToUpperCase

class TrainingItemRVAdapter :
    ListAdapter<TrainingCommonInfo, TrainingItemViewHolder>(TrainingItemDiffCallBack()) {

    var onBtnAddAmountClickListener: ((TrainingCommonInfo) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TrainingItemViewHolder(
            layoutInflater.inflate(
                R.layout.training_item_full,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: TrainingItemViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            tvTrainingExerciseName.text = item.exerciseName.firstCharToUpperCase()
            tvExerciseAmountOfRepeat.text = item.countRepeat.toString()
            tvTotalDuration.text = item.getFormattedDuration()
            tvAvgWeight.text = "${item.avgWeight}kg"
            btnAddAmount.setOnClickListener {
                onBtnAddAmountClickListener?.invoke(item)
            }
        }


    }

}