package fitness.travel.onxwjvbr.ui.exercise_list.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import fitness.travel.onxwjvbr.R
import fitness.travel.onxwjvbr.data.ExerciseItemDB

class ExercisesRVAdapter:ListAdapter<ExerciseItemDB, ExerciseViewHolder>(ExercisesDiffCallBack) {

    var onBtnAddClickListener : ((ExerciseItemDB) -> Unit)? = null
    var onItemClickListener : ((ExerciseItemDB) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = R.layout.exercise_item
        return ExerciseViewHolder(
            layoutInflater.inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val item = getItem(position)
        with(holder){
            tvName.text = item.name
            tvBodyPart.text = item.bodyPart
            tvEquipment.text = item.equipment
            tvTarget.text = item.target
            tvSecondaryMuscles.text = item.secondaryMuscles
            btnAdd.setOnClickListener {
                onBtnAddClickListener?.invoke(item)
            }
            btnExpand.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }
}