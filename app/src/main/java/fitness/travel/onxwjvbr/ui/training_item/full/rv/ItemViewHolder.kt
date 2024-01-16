package fitness.travel.onxwjvbr.ui.training_item.full.rv

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fitness.travel.onxwjvbr.R

class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvDuration = itemView.findViewById<TextView>(R.id.tvDuration)
    val tvWeight = itemView.findViewById<TextView>(R.id.tvWeight)
    val tvAmount = itemView.findViewById<TextView>(R.id.tvAmount)
}