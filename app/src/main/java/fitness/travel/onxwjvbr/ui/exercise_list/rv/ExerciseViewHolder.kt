package fitness.travel.onxwjvbr.ui.exercise_list.rv

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import fitness.travel.onxwjvbr.R

class ExerciseViewHolder(itemView:View):ViewHolder(itemView) {
    val tvName = itemView.findViewById<TextView>(R.id.tvName)
    val btnAdd = itemView.findViewById<ImageView>(R.id.buttonAdd)
    val btnExpand = itemView.findViewById<ImageView>(R.id.btnExpand)
    val tvBodyPart = itemView.findViewById<TextView>(R.id.tvBodyPartValue)
    val tvEquipment = itemView.findViewById<TextView>(R.id.tvEquipmentValue)
    val tvTarget = itemView.findViewById<TextView>(R.id.tvTargetValue)
    val tvSecondaryMuscles = itemView.findViewById<TextView>(R.id.tvSecondaryMusclesValue)
}