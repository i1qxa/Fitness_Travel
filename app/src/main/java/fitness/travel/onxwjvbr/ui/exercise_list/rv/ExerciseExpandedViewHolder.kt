package fitness.travel.onxwjvbr.ui.exercise_list.rv

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import fitness.travel.onxwjvbr.R

class ExerciseExpandedViewHolder(itemView:View):ViewHolder(itemView) {
    val tvName = itemView.findViewById<TextView>(R.id.tvNameExp)
    val btnAdd = itemView.findViewById<ImageView>(R.id.buttonAddExp)
    val btnExpand = itemView.findViewById<ImageView>(R.id.btnCollapse)
    val ivInstruction = itemView.findViewById<ImageView>(R.id.ivExerciseInstruction)
    val tvInstruction = itemView.findViewById<TextView>(R.id.tvInstruction)
}