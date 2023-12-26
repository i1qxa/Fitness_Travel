package fitness.travel.onxwjvbr.ui.training_item.rv

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import fitness.travel.onxwjvbr.R

class TrainingItemViewHolder(itemView:View):ViewHolder(itemView) {
    val tvTrainingExerciseName = itemView.findViewById<TextView>(R.id.tvTrainingExerciseName)
    val tvExerciseAmountOfRepeat = itemView.findViewById<TextView>(R.id.tvExerciseAmountRepeat)
    val btnAddAmount = itemView.findViewById<ImageButton>(R.id.btnAddRepeat)
}