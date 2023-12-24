package fitness.travel.onxwjvbr.ui.my_exercise_list.rv

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fitness.travel.onxwjvbr.R

class MyExercisesVH(itemView: View): RecyclerView.ViewHolder(itemView) {

    val tvName = itemView.findViewById<TextView>(R.id.tvName)
    val btnRemove = itemView.findViewById<ImageView>(R.id.buttonRemove)
    val tvBodyPart = itemView.findViewById<TextView>(R.id.tvBodyPartValue)
    val tvEquipment = itemView.findViewById<TextView>(R.id.tvEquipmentValue)
    val tvTarget = itemView.findViewById<TextView>(R.id.tvTargetValue)
    val tvSecondaryMuscles = itemView.findViewById<TextView>(R.id.tvSecondaryMusclesValue)

}