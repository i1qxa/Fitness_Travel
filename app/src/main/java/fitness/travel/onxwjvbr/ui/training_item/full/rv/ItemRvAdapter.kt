package fitness.travel.onxwjvbr.ui.training_item.full.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.data.training.training_item.TrainingItemDB
import fitness.travel.onxwjvbr.ui.training_item.rv.TrainingItemDiffCallBack

class ItemRvAdapter: ListAdapter<TrainingItemDB, ItemViewHolder>(ItemDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(
            layoutInflater.inflate(
                R.layout.exercise_in_training,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        with(holder){
            tvDuration.text = item.getFormattedDuration()
            tvWeight.text = "${item.weight}kg"
            tvAmount.text = item.amountRepeat.toString()
        }
    }
}