package fitness.travel.onxwjvbr.ui.training_item.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.data.exercise.ExerciseItemDB
import fitness.travel.onxwjvbr.data.training.training_item.TrainingItemDB

class TrainingItemRVAdapter :
    ListAdapter<TrainingItemDB, TrainingItemViewHolder>(TrainingItemDiffCallBack()) {

    var onBtnAddAmountClickListener: ((TrainingItemDB) -> Unit)? = null
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
            tvTrainingExerciseName.text = item.exerciseName
            tvExerciseAmountOfRepeat.text = this.itemView.context.getString(R.string.amount_of_repeat, item.amountRepeat)
            btnAddAmount.setOnClickListener {
                onBtnAddAmountClickListener?.invoke(item)
            }
        }


    }

}