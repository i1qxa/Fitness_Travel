package fitness.travel.onxwjvbr.ui.training_list.rv

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import fitness.travel.onxwjvbr.R

class TrainingListViewHolder(itemView:View):ViewHolder(itemView) {
    val tvTrainingDate = itemView.findViewById<TextView>(R.id.tvTrainingDate)
    val tvTrainingDuration = itemView.findViewById<TextView>(R.id.tvTrainingDuration)
    val tvTrainingDay = itemView.findViewById<TextView>(R.id.tvTrainingDay)
}