package fitness.travel.onxwjvbr.ui.training_list.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.data.training.TrainingDB

class TrainingRVAdapter :
    ListAdapter<TrainingDB, TrainingListViewHolder>(TrainingDiffCallBack()) {

    var onItemClickListener: ((TrainingDB) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TrainingListViewHolder(
            layoutInflater.inflate(
                R.layout.training_item,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: TrainingListViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            tvTrainingDate.text = item.getFormattedDate()
            tvTrainingDuration.text = item.getFormattedDuration()
            tvTrainingDay.text = item.getDayAsString()
            holder.itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }

}